package com.example.qaraqalpaqjazwshlar.main

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqjazwshlar.R
import com.example.qaraqalpaqjazwshlar.settings.SettingsFragment
import com.example.qaraqalpaqjazwshlar.settings.SettingsFragment.Companion.DAY_LIGHT
import com.example.qaraqalpaqjazwshlar.settings.SettingsFragment.Companion.DAY_NIGHT
import com.example.qaraqalpaqjazwshlar.settings.SettingsFragment.Companion.FILE_SETTINGS
import com.example.qaraqalpaqjazwshlar.settings.SettingsFragment.Companion.KEY_NIGHT_MODE
import com.example.qaraqalpaqjazwshlar.favorite.FragmentFavorite
import com.example.qaraqalpaqjazwshlar.info.FragmentInfo
import com.example.qaraqalpaqjazwshlar.poets.FragmentPoets
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import java.util.*


class MainActivity : AppCompatActivity() {
    private val fragmentPoets = FragmentPoets()
    private val fragmentChosen = FragmentFavorite()
    private val fragmentInfo = FragmentInfo()
    private val fragmentSettings = SettingsFragment()
    private lateinit var menuItem: MenuItem
    private lateinit var preferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        preferences = getSharedPreferences(FILE_SETTINGS, Context.MODE_PRIVATE)
        if (preferences.getBoolean(KEY_NIGHT_MODE, false)) {
            AppCompatDelegate.setDefaultNightMode(DAY_NIGHT)
        }
        else AppCompatDelegate.setDefaultNightMode(DAY_LIGHT)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val toggle = ActionBarDrawerToggle(
            this,
            drawer_layout,
            toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.setNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.nav_poets -> fragmentContainer.replace(fragmentPoets, 1)
                R.id.nav_chosen -> fragmentContainer.replace(fragmentChosen, 2)
                R.id.nav_info -> fragmentContainer.replace(fragmentInfo, 3)
                R.id.nav_settings -> fragmentContainer.replace(fragmentSettings, 4)
                else -> return@setNavigationItemSelectedListener false
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            AlertDialog.Builder(this)
                .setTitle(R.string.menu_poets)
                .setIcon(R.mipmap.ic_launcher_poet)
                .setMessage("Сиз бағдарламаны тәрк етпекшисиз бе?")
                .setPositiveButton("Аўа") { _, _ ->
                    finish()
                }
                .setNegativeButton("Яқ") { d, _ ->
                    d.dismiss()
                }
                .create()
                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_search, menu)
        menuItem = menu!!.findItem(R.id.action_search)
        val viewSearch = menuItem.actionView as androidx.appcompat.widget.SearchView
        viewSearch.queryHint = getString(R.string.search)
        viewSearch.setOnQueryTextListener(object :
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                viewSearch.clearFocus()
                fragmentPoets.presenter.filter(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                fragmentPoets.presenter.filter(newText!!)
                return false
            }
        })
        if (preferences.getBoolean("open",false))
            fragmentContainer.replace(fragmentSettings,4)
        else
            fragmentContainer.replace(fragmentPoets,1)

        preferences.edit().putBoolean("open",false).apply()
        return super.onCreateOptionsMenu(menu)
    }

    private fun View.replace(fragment: Fragment, position: Int) {
        supportFragmentManager.beginTransaction().replace(this.id, fragment).commit()
        menuItem.isVisible = false
        when (position) {
            1 -> {
                toolbar.title = getString(R.string.menu_poets)
                menuItem.isVisible = true
                nav_view.setCheckedItem(R.id.nav_poets)
            }
            2 -> {
                toolbar.title = getString(R.string.menu_chosen)
                nav_view.setCheckedItem(R.id.nav_chosen)
            }
            3 -> {
                toolbar.title = getString(R.string.menu_info)
                nav_view.setCheckedItem(R.id.nav_info)
            }
            4 -> {
                toolbar.title = getString(R.string.menu_settings)
                nav_view.setCheckedItem(R.id.nav_settings)
            }

        }

    }

}