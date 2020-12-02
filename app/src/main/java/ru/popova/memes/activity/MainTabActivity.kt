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
    private val idToFragmentMap: Map<Int, Fragment> = mapOf(
        Pair(R.id.action_meme_list, MemeListFragment()),
        Pair(R.id.action_new_meme, NewMemeFragment()),
        Pair(R.id.action_profile, ProfileFragment())
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_tab)
        supportActionBar?.hide();

        val navbar: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        navbar.setOnNavigationItemSelectedListener {
            openFragment(it.itemId, navbar)
            true
        }
        openFragment(R.id.action_meme_list, navbar)
    }

    private fun openFragment(itemId: Int, navbar: BottomNavigationView) {
        val fragment = idToFragmentMap.getValue(itemId)
        supportFragmentManager.beginTransaction().replace(R.id.tab_frame, fragment).commit()
        navbar.menu.findItem(itemId).isEnabled = false
        idToFragmentMap.keys
            .filter { k -> k != itemId }
            .forEach { k ->
                navbar.menu.findItem(k).isEnabled = true
            }
    }
}