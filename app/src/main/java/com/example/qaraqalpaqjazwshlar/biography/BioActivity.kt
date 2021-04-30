package com.example.qaraqalpaqjazwshlar.biography

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.example.qaraqalpaqjazwshlar.R
import kotlinx.android.synthetic.main.avtivity_bio.*

class BioActivity : AppCompatActivity(), BiographyView {
    private var favoriteItem: MenuItem? = null
    private lateinit var presenter: BiographyPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avtivity_bio)
        val id= intent.getIntExtra("id", 1)
        presenter=BiographyPresenter(this,this,id)
        toolbar_bio.setTitle(R.string.menu_poets)
        toolbar_bio.title =getString(R.string.menu_poets)
        setSupportActionBar(toolbar_bio)

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        presenter.getBiography()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.biography_toolbar, menu)
        favoriteItem = menu!!.findItem(R.id.favorite)
        presenter.setBookmark()
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
            }
            R.id.favorite -> {
                presenter.changeBookmark()
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

    override fun changeBookmark(isPressed: Boolean) {
        if (isPressed) favoriteItem!!.setIcon(R.drawable.ic_baseline_bookmark_24)
        else favoriteItem!!.setIcon(R.drawable.ic_baseline_bookmark_border_24)
    }

    override fun share(text: CharSequence) {
        val sharingIntent = Intent(Intent.ACTION_SEND)
        sharingIntent.type = "text/plain"
        sharingIntent.putExtra(Intent.EXTRA_TEXT, text)
        sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
        startActivity(Intent.createChooser(sharingIntent, "Bólisiw"))
    }
}