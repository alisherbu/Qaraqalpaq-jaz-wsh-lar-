package com.example.qaraqalpaqjazwshlar.biography

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.qaraqalpaqjazwshlar.R
import kotlinx.android.synthetic.main.avtivity_bio.*

class BioActivity : AppCompatActivity(), BiographyView {
    lateinit var presenter: BiographyPresenter
    companion object{
        const val FIRST_PRESSED=true
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avtivity_bio)
        val id= intent.getIntExtra("id", 1)
        presenter=BiographyPresenter(this,this,id)
        toolbar_bio.setTitle(R.string.menu_poets)
        setSupportActionBar(toolbar_bio)

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        presenter.getBiography()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.biography_toolbar, menu)
        presenter.menuItem = menu!!.findItem(R.id.favorite)
        presenter.changeBookmark(!FIRST_PRESSED)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.favorite -> {
                presenter.changeBookmark(FIRST_PRESSED)
            }
            R.id.share -> {
                presenter.share(tvBio.text)
            }
            else -> return false
        }
        return true
    }

    override fun getBiography(biography: String, poetName: String, lifeSpan: String) {
        tvPoetName.text = poetName
        tvPoetLife.text = lifeSpan
        tvBio.text = biography
    }
}