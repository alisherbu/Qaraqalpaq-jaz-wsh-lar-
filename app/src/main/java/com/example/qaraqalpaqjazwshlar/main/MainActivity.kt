package com.example.qaraqalpaqjazwshlar.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqjazwshlar.R
import com.example.qaraqalpaqjazwshlar.favorite.FragmentFavorite
import com.example.qaraqalpaqjazwshlar.info.FragmentInfo
import com.example.qaraqalpaqjazwshlar.poets.FragmentPoets
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import java.util.*


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private val fragmentPoets = FragmentPoets()
    private val fragmentChosen = FragmentFavorite()
    private val fragmentInfo = FragmentInfo()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        toolbar.title = getString(R.string.menu_poets)
        setSupportActionBar(toolbar)
        fragmentContainer.replace(fragmentPoets)
        val toggle = ActionBarDrawerToggle(this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.nav_poets -> {
                    toolbar.title = getString(R.string.menu_poets)
                    fragmentContainer.replace(fragmentPoets)
                }
                R.id.nav_chosen -> {
                    toolbar.title = getString(R.string.menu_chosen)
                    fragmentContainer.replace(fragmentChosen)
                }
                R.id.nav_info -> {
                    toolbar.title = getString(R.string.menu_info)
                    fragmentContainer.replace(fragmentInfo)
                }
                else -> return@setNavigationItemSelectedListener false
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else super.onBackPressed()
    }

    private fun View.replace(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(this.id, fragment).commit()
    }

}