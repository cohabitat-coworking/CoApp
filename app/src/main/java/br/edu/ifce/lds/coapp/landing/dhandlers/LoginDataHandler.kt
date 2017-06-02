package br.edu.ifce.lds.coapp.landing.dhandlers

import android.app.Activity
import android.content.Context
import android.util.Log
import br.edu.ifce.lds.coapp.application.UserSession
import br.edu.ifce.lds.coapp.landing.entities.User
import br.edu.ifce.lds.coapp.landing.presenters.LoginPresenter
import br.edu.ifce.lds.coapp.utils.PreferencesUtil
import com.google.firebase.auth.AuthCredential
import com.google.firebase.auth.FirebaseAuth

/**
 * Lower level communication class responsible for dealing with the database (Firebase)
 */

class LoginDataHandler(val mPresenter: LoginPresenter, val context: Context) {

    var mAuth: FirebaseAuth
    var mAuthListener: FirebaseAuth.AuthStateListener
    var mPrefs: PreferencesUtil

    val TAG = "LoginDataHandler"

    /**
     * Starts up the peferences, firebase auth for authentication
     */
    init {
        //shared prefenreces for saving user information
        mPrefs = PreferencesUtil(context)

        //firebase auth for authentication
        mAuth = FirebaseAuth.getInstance()

        //athentication listener for keeping track when the user logs in and off
        mAuthListener = FirebaseAuth.AuthStateListener { firebaseAuth ->
            val user = firebaseAuth.currentUser
            if (user != null) {
                // User is signed in
                Log.d(TAG, "onAuthStateChanged:signed_in:" + user.uid)
            } else {
                // User is signed out
                Log.d(TAG, "onAuthStateChanged:signed_out")
            }

        }
    }


    /**
     * Self explanatory
     */
    fun turnOnAuthenticationListener() {
        mAuth.addAuthStateListener(mAuthListener)
    }


    /**
     * Self explanatory
     */
    fun turnOffOnAuthenticationListener() {
        mAuth.removeAuthStateListener(mAuthListener)
    }

    /**
     * Logs user using an email and a password
     */
    fun authenticateWithPassword(username: String, password: String) {
        mAuth.signInWithEmailAndPassword(username, password)
                .addOnCompleteListener(context as Activity) { task ->

                    if (!task.isSuccessful) {
                        //if log in failed, ask presente to show error message
                        Log.w(TAG, "signInWithEmail:failed", task.exception)
                        mPresenter.authenticationFailed()
                    } else {

                        //if log in was sucessful
                        val user = mAuth.currentUser

                        //saves user info to shared preferences
                        if (user != null) {
                            val userF = User("", username, user.uid)

                            val session = UserSession(mPrefs)
                            session.signin(userF)

                            //tells the vuew that it was sucessful
                            mPresenter.authenticationSuceeded()

                        }
                    }
                }
    }


    /**
     * Logs an user using facebook credential, same idea as previously
     */
    fun authenticateWithFacebook(credential: AuthCredential?) {
        if (credential != null) {
            mAuth.signInWithCredential(credential)
                    .addOnCompleteListener(context as Activity) {
                        task ->
                        Log.d(TAG, "signInWithCredential:onComplete:" + task.isSuccessful)
                        if (!task.isSuccessful) {
                            mPresenter.authenticationFailed()
                        } else {
                            val user = mAuth.currentUser


                            if (user != null) {

                                val userName: String = user.displayName?.let { it } ?: ""
                                val userEmail: String = user.email?.let { it } ?: ""

                                val userF = User(userName, userEmail, "", user.uid)

                                val session = UserSession(mPrefs)
                                session.signin(userF)
                                mPresenter.authenticationSuceeded()
                            }
                        }

                    }
        } else {
            mPresenter.authenticationFailed()
        }

    }


}
