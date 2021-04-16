package com.example.qaraqalpaqjazwshlar

import android.os.Bundle
import android.view.Menu
import android.view.View
import androidx.appcompat.app.ActionBarDrawerToggle
import com.google.android.material.navigation.NavigationView
import androidx.drawerlayout.widget.DrawerLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.example.qaraqalpaqjazwshlar.ui.FragmentPoets
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {
    private val fragmentPoets=FragmentPoets()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        fragmentContainer.replace(fragmentPoets)
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
        val navView: NavigationView = findViewById(R.id.nav_view)
        val toggle=ActionBarDrawerToggle(this,drawerLayout,toolbar, R.string.navigation_drawer_open,R.string.navigation_drawer_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }
    private fun View.replace(fragment:Fragment){
        supportFragmentManager.beginTransaction().replace(this.id,fragment).commit()
    }
}