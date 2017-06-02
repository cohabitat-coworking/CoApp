package br.edu.ifce.lds.coapp.landing.entities

import java.util.prefs.Preferences

/**
 * Models a user
 */
data class User(val name: String,
                val email: String,
                val cpf: String = "",
                val uid: String = "",
                val password: String = "")