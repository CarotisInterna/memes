package ru.popova.memes.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import ru.popova.memes.R

class MainTabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tab)
        supportActionBar?.hide()
    }
}