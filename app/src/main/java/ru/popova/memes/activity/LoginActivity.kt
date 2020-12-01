package ru.popova.memes.activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.widget.Button
import android.widget.ProgressBar
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import ru.popova.memes.R
import ru.popova.memes.dto.LoginRequestDto
import ru.popova.memes.task.TaskManager
import ru.popova.memes.util.Failure
import ru.popova.memes.util.Success
import studio.carbonylgroup.textfieldboxes.ExtendedEditText
import studio.carbonylgroup.textfieldboxes.TextFieldBoxes

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()

        val loginTextBox: TextFieldBoxes = findViewById(R.id.login_text_box)
        val passwordTextBox: TextFieldBoxes = findViewById(R.id.password_text_box)
        val loginField: ExtendedEditText = findViewById(R.id.login_field)
        val passwordField: ExtendedEditText = findViewById(R.id.password_field)

        val loginButton: Button = findViewById(R.id.login_button)

        loginButton.setOnClickListener {
            val fields = mapOf(Pair(loginTextBox, loginField), Pair(passwordTextBox, passwordField))
            val noErrors =
                fields
                    .filter { it.value.text.isBlank() }
                    .onEach { it.key.setError("Поле не может быть пустым", false) }
                    .isEmpty()
            if (noErrors) {
                val progressBar: ProgressBar = findViewById(R.id.progress_bar)
                val loginRequestDto = LoginRequestDto(
                    loginField.text.toString(),
                    passwordField.text.toString()
                )
                loginButton.visibility = Button.GONE
                progressBar.visibility = ProgressBar.VISIBLE
                //Emulate long process
                Handler().postDelayed({
                    val task = TaskManager.loginTask.execute(loginRequestDto)
                    when (val result = task.get()) {
                        is Success -> {
                            Log.i(LoginActivity::class.toString(), "Success:${result.value}")
                            startActivity(Intent(this@LoginActivity, MainTabActivity::class.java))
                        }
                        is Failure -> {
                            val snackbar = Snackbar.make(
                                findViewById(R.id.login_view),
                                "Произошла ошибка при входе.Попробуйте позже.",
                                Snackbar.LENGTH_LONG
                            )
                            snackbar.view.setBackgroundColor(resources.getColor(R.color.snackbar))
                            snackbar.show()
                        }
                    }
                    progressBar.visibility = ProgressBar.GONE
                    loginButton.visibility = Button.VISIBLE
                }, 3000)

            }
        }
    }
}