package com.example.qaraqalpaqjazwshlar.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.qaraqalpaqjazwshlar.R
import com.example.qaraqalpaqjazwshlar.data.PoetsDao
import com.example.qaraqalpaqjazwshlar.data.PoetsDatabase
import kotlinx.android.synthetic.main.avtivity_bio.*

class BioActivity : AppCompatActivity() {
    var id = 1
    var pos = 0
    lateinit var dao: PoetsDao
    lateinit var menuItem: MenuItem
    private var isFavorite: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avtivity_bio)

        setSupportActionBar(toolbar2)
        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        dao = PoetsDatabase.getInstance(this).dao()
        id = intent.getIntExtra("id", 1)
        pos = intent.getIntExtra("pos", 0)
        val biography = dao.getBiographyById(id)

        tvPoetName.text = dao.getPoetNameById(id)
        tvPoetLife.text = dao.getLifeSpanById(id)

        tvBio.text = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            Html.fromHtml(biography, Html.FROM_HTML_MODE_COMPACT)
        } else {
            Html.fromHtml(biography)
        }
        isFavorite = dao.getStatus(id) == 1

    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.biography_toolbar, menu)
        menuItem = menu!!.findItem(R.id.favorite)
        if (isFavorite) menuItem.setIcon(R.drawable.ic_baseline_bookmark_24)
        else menuItem.setIcon(R.drawable.ic_baseline_bookmark_border_24)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.favorite -> {
                if (isFavorite) {
                    menuItem.setIcon(R.drawable.ic_baseline_bookmark_border_24)
                    dao.setStatus(id, 0)
                    isFavorite =false

                } else {
                    menuItem.setIcon(R.drawable.ic_baseline_bookmark_24)
                    dao.setStatus(id, 1)
                    isFavorite = true
                    dao.setDate(id, System.currentTimeMillis())
                }
            }
            R.id.share -> {
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                val shareBody = "${tvBio.text}"
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject here")
                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody)
                startActivity(Intent.createChooser(sharingIntent, "Bólisiw"));

            }
            else -> return false
        }
        return true
    }


}