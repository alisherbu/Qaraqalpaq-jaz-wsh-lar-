package com.example.qaraqalpaqjazwshlar.activity

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqjazwshlar.R
import com.example.qaraqalpaqjazwshlar.favorite.FragmentFavorite
import com.example.qaraqalpaqjazwshlar.fragment.FragmentInfo
import com.example.qaraqalpaqjazwshlar.poets.FragmentPoets
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val fragmentPoets = FragmentPoets()
    private val fragmentChosen = FragmentFavorite()
    private val fragmentInfo=FragmentInfo()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        fragmentContainer.replace(fragmentPoets)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        nav_view.setNavigationItemSelectedListener {

            when (it.itemId) {
                R.id.nav_poets -> {
                    fragmentContainer.replace(fragmentPoets)
                }
                R.id.nav_chosen -> {
                    fragmentContainer.replace(fragmentChosen)
                }
                R.id.nav_info -> {
                    fragmentContainer.replace(fragmentInfo)
                }
                else ->return@setNavigationItemSelectedListener false
            }
            drawerLayout.closeDrawer(GravityCompat.START)
return@setNavigationItemSelectedListener true
        }

    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)){
            drawer_layout.closeDrawer(GravityCompat.START)
        }
        else super.onBackPressed()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    private fun View.replace(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(this.id, fragment).commit()
    }
}