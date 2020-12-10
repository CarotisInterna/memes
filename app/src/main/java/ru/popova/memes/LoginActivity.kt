package ru.popova.memes

import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import studio.carbonylgroup.textfieldboxes.ExtendedEditText

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        val loginField: ExtendedEditText = findViewById(R.id.login_field)
        val passwordField: ExtendedEditText = findViewById(R.id.password_field)

        val loginButton: Button = findViewById(R.id.login_button)

        loginButton.setOnClickListener {
            val login = loginField.text
            val pass = passwordField.text
            if (login.isBlank()) {
                Toast.makeText(this, "Логин не может быть пустым", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            if (pass.isBlank()) {
                Toast.makeText(this, "Пароль не может быть пустым", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

        }
    }
}