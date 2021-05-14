package com.example.qaraqalpaqjazwshlar.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.GravityCompat
import androidx.core.view.MenuItemCompat
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqjazwshlar.R
import com.example.qaraqalpaqjazwshlar.favorite.FragmentFavorite
import com.example.qaraqalpaqjazwshlar.info.FragmentInfo
import com.example.qaraqalpaqjazwshlar.poets.FragmentPoets
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import java.util.*


@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private val fragmentPoets = FragmentPoets()
    private val fragmentChosen = FragmentFavorite()
    private val fragmentInfo = FragmentInfo()
   lateinit var menuItem:MenuItem
   var nightMode=true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toolbar.title = getString(R.string.menu_poets)
        setSupportActionBar(toolbar)
        fragmentContainer.replace(fragmentPoets)
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
                R.id.nav_poets -> {
                    toolbar.title = getString(R.string.menu_poets)
                    fragmentContainer.replace(fragmentPoets)
                    menuItem.isVisible=true
                }
                R.id.nav_chosen -> {
                    toolbar.title = getString(R.string.menu_chosen)
                    fragmentContainer.replace(fragmentChosen)
                    menuItem.isVisible=false
                }
                R.id.nav_info -> {
                    toolbar.title = getString(R.string.menu_info)
                    fragmentContainer.replace(fragmentInfo)
                    menuItem.isVisible=false
                }
                else -> return@setNavigationItemSelectedListener false
            }
            drawer_layout.closeDrawer(GravityCompat.START)
            return@setNavigationItemSelectedListener true
        }
        val headerView = nav_view.getHeaderView(0)
        headerView.night.setOnClickListener {
            nightMode = !nightMode
            if (nightMode) {
                Toast.makeText(this,"Light",Toast.LENGTH_SHORT).show()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
            else {
                Toast.makeText(this,"Night",Toast.LENGTH_SHORT).show()
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            }

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
        val inflater=menuInflater
        inflater.inflate(R.menu.menu_search,menu)
        menuItem=menu!!.findItem(R.id.action_search)
        val viewSearch=MenuItemCompat.getActionView(menuItem) as androidx.appcompat.widget.SearchView
        viewSearch.queryHint=getString(R.string.search)
        viewSearch.setOnQueryTextListener(object :androidx.appcompat.widget.SearchView.OnQueryTextListener{
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
        return super.onCreateOptionsMenu(menu)
    }
    private fun View.replace(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(this.id, fragment).commit()
    }
}