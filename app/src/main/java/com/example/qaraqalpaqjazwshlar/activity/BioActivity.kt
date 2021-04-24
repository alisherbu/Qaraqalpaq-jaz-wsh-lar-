package com.example.qaraqalpaqjazwshlar.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.view.WindowManager
import android.widget.Toast
import android.widget.Toolbar
import androidx.appcompat.app.AppCompatActivity
import com.example.qaraqalpaqjazwshlar.R
import com.example.qaraqalpaqjazwshlar.data.PoetsDao
import com.example.qaraqalpaqjazwshlar.data.PoetsDatabase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.app_bar_main.*
import kotlinx.android.synthetic.main.avtivity_bio.*
import kotlinx.android.synthetic.main.avtivity_bio.toolbar2
import java.lang.reflect.Type
import java.util.ArrayList

class BioActivity : AppCompatActivity() {
     var  favoriteList = arrayListOf<Int>()
    private var isfavorite=false
    var id=1
    lateinit var dao:PoetsDao
    lateinit var menuItem: MenuItem
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avtivity_bio)

        setSupportActionBar(toolbar2)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        dao=PoetsDatabase.getInstance(this).dao()
        id = intent.getIntExtra("id",1)
        val biography = dao.getBiographyById(id)

        tvPoetName.text=dao.getPoetNameById(id)
        tvPoetLife.text=dao.getLifeSpanById(id)

        tvBio.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(biography, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(biography)
        }


    }



    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.biography_toolbar,menu)
        menuItem=menu!!.findItem(R.id.favorite)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home->{
                finish()
            }
            R.id.favorite->{
                if (isfavorite){
                menuItem.setIcon(R.drawable.ic_baseline_bookmark_border_24)
                    isfavorite=false
                    favoriteList.remove(id)
                }
                else {
                    menuItem.setIcon(R.drawable.ic_baseline_bookmark_24)
                    isfavorite=true
                    favoriteList.add(id)
                }
                saveArrayList(favoriteList,"fav")
            }
            R.id.share->{
            val sharingIntent=Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                val shareBody="${tvBio.text}"
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT,"Subject here")
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                startActivity(Intent.createChooser(sharingIntent, "Bólisiw"));

            }
            else -> return false
        }
        return true
    }
    fun saveArrayList(list: ArrayList<Int>?, key: String?) {
        val prefs: SharedPreferences =
                getSharedPreferences("MySharedPreferences", Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = prefs.edit()
        val gson = Gson()
        val json: String = gson.toJson(list)
        editor.putString(key, json)
        editor.apply()
    }


}