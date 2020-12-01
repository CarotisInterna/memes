package ru.popova.memes.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import ru.popova.memes.R
import ru.popova.memes.activity.fragment.MemeListFragment
import ru.popova.memes.activity.fragment.NewMemeFragment
import ru.popova.memes.activity.fragment.ProfileFragment

class MainTabActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tab)
        supportActionBar?.hide();
        openFragment(MemeListFragment())

        val navbar: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        navbar.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.action_meme_list -> MemeListFragment()
                R.id.action_new_meme -> NewMemeFragment()
                R.id.action_profile -> ProfileFragment()
                else -> null
            }?.let { fragment ->
                openFragment(fragment)
            }

            true
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.tab_frame, fragment).commit()
    }
}