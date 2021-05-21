package uz.texnopos.jaziwshilar.main

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import uz.texnopos.jaziwshilar.favorite.FragmentFavorite
import uz.texnopos.jaziwshilar.info.FragmentInfo
import uz.texnopos.jaziwshilar.poets.FragmentPoets
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.content_main.*
import kotlinx.android.synthetic.main.nav_header_main.*
import kotlinx.android.synthetic.main.nav_header_main.view.*
import uz.texnopos.jaziwshilar.R
import java.util.*


class MainActivity : AppCompatActivity() {
    private val fragmentPoets = FragmentPoets()
    private val fragmentChosen = FragmentFavorite()
    private val fragmentInfo = FragmentInfo()
    private lateinit var menuItem: MenuItem
    private lateinit var preferences: SharedPreferences
    private lateinit var appBarConfiguration: AppBarConfiguration
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        preferences = getSharedPreferences("fragment", Context.MODE_PRIVATE)
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
//TODO
        val navController=findNavController(R.id.nav_host_fragment_content_main)
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow
            ), drawer_layout
        )
        setupActionBarWithNavController(navController, appBarConfiguration)

        nav_view.setupWithNavController(navController)
//        nav_view.setNavigationItemSelectedListener {
//            menuItem.isVisible = false
//            when (it.itemId) {
//                R.id.nav_poets -> fragmentContainer.replace(fragmentPoets, 1)
//                R.id.nav_chosen -> fragmentContainer.replace(fragmentChosen, 2)
//                R.id.nav_info -> fragmentContainer.replace(fragmentInfo, 3)
//                else -> return@setNavigationItemSelectedListener false
//            }
//            drawer_layout.closeDrawer(GravityCompat.START)
//            return@setNavigationItemSelectedListener true
//        }
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController=findNavController(R.id.nav_host_fragment_content_main)

        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
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
                    preferences.edit().putInt("fragment", 1).apply()
                    finish()
                }
                .setNegativeButton("Яқ") { d, _ ->
                    d.dismiss()
                }
                .setNeutralButton("RATE APP") { _, _ ->
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse("https://play.google.com/store")
                        )
                    )
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
        when (preferences.getInt("fragment", 1)) {
            1 -> fragmentContainer.replace(fragmentPoets, 1)
            2 -> fragmentContainer.replace(fragmentChosen, 2)
            3 -> fragmentContainer.replace(fragmentInfo, 3)
        }
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
        }
        preferences.edit().putInt("fragment", position).apply()
    }
}