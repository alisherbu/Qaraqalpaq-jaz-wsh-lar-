package com.alisherbu.writers.biography

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import com.alisherbu.writers.R
import com.alisherbu.writers.data.PoetsDatabase
import com.alisherbu.writers.databinding.ActivityBioBinding
import com.google.android.material.snackbar.Snackbar

class BioActivity : AppCompatActivity(), BiographyView {
    private lateinit var binding: ActivityBioBinding
    private var favoriteItem: MenuItem? = null
    private lateinit var presenter: BiographyPresenter
    private val dao = PoetsDatabase.getInstance(this).dao()
    private var toast = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBioBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val id = intent.getIntExtra("id", 1)
        presenter = BiographyPresenter(dao, this, id)
        presenter.getBiography()
        setSupportActionBar(binding.toolbar)

        supportActionBar!!.setHomeAsUpIndicator(R.drawable.ic_baseline_arrow_back_24)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
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
                val sharingIntent = Intent(Intent.ACTION_SEND)
                sharingIntent.type = "text/plain"
                sharingIntent.putExtra(Intent.EXTRA_TEXT, binding.tvBio.text)
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
                startActivity(Intent.createChooser(sharingIntent, "Бөлисиў"))
            }
            else -> return false
        }
        return true
    }

    override fun getBiography(biography: String, poetName: String, lifeSpan: String) {
        binding.tvPoetName.text = poetName
        binding.tvPoetLife.text = lifeSpan
        binding.tvBio.text = HtmlCompat.fromHtml(biography, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    override fun changeBookmark(isPressed: Boolean) {
        if (isPressed) {
            if (toast) Snackbar.make(binding.ln, "Сайландыларға қосылды", Snackbar.LENGTH_SHORT)
                .show()
            favoriteItem!!.setIcon(R.drawable.ic_baseline_bookmark_24)
        } else {
            if (toast) Snackbar.make(binding.ln, "Сайландылардан өширилди", Snackbar.LENGTH_SHORT)
                .show()
            favoriteItem!!.setIcon(R.drawable.ic_baseline_bookmark_border_24)
        }
        toast = true
    }
}
