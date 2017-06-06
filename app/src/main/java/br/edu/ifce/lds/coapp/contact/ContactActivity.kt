package br.edu.ifce.lds.coapp.contact

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.transition.Slide
import br.edu.ifce.lds.coapp.R
import br.edu.ifce.lds.coapp.common.BaseActivity
import kotlinx.android.synthetic.main.activity_contact.*

class ContactActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact)

        rbtEmail.isChecked = true
        setUpViews()
    }

    private fun setUpViews() {
        val slide = Slide()
        slide.duration = 1000
        window.enterTransition = slide
    }
}