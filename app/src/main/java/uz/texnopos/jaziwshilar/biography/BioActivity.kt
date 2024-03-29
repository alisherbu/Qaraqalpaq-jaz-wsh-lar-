package uz.texnopos.jaziwshilar.biography

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import androidx.core.text.HtmlCompat
import uz.texnopos.jaziwshilar.data.PoetsDatabase
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.avtivity_bio.*
import uz.texnopos.jaziwshilar.R

class BioActivity : AppCompatActivity(), BiographyView {
    private var favoriteItem: MenuItem? = null
    private lateinit var presenter: BiographyPresenter
    private val dao = PoetsDatabase.getInstance(this).dao()
    private var toast = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avtivity_bio)

        val fromLeft = AnimationUtils.loadAnimation(this, R.anim.from_left)
        fromLeft.startOffset = 300
        tvPoetName.startAnimation(fromLeft)
        val fadeFromIn = AnimationUtils.loadAnimation(this, R.anim.fade_from_in)
        fadeFromIn.startOffset = 800
        tvPoetLife.startAnimation(fadeFromIn)
        val fromBottom = AnimationUtils.loadAnimation(this, R.anim.from_bottom)
        fromBottom.startOffset = 1000
        tvBio.startAnimation(fromBottom)

        val id = intent.getIntExtra("id", 1)
        presenter = BiographyPresenter(dao, this, id)
        presenter.getBiography()
        setSupportActionBar(toolbar_bio)

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
                sharingIntent.putExtra(Intent.EXTRA_TEXT, tvBio.text)
                sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
                startActivity(Intent.createChooser(sharingIntent, "Бөлисиў"))
            }
            else -> return false
        }
        return true
    }

    override fun getBiography(biography: String, poetName: String, lifeSpan: String) {
        tvPoetName.text = poetName
        tvPoetLife.text = lifeSpan
        tvBio.text = HtmlCompat.fromHtml(biography, HtmlCompat.FROM_HTML_MODE_COMPACT)
    }

    override fun changeBookmark(isPressed: Boolean) {
        if (isPressed) {
            if (toast) Snackbar.make(ln, "Сайландыларға қосылды", Snackbar.LENGTH_SHORT)
                .setBackgroundTint(Color.BLACK)
                .setTextColor(Color.WHITE)
                .show()
            favoriteItem!!.setIcon(R.drawable.ic_baseline_bookmark_24)
        } else {
            if (toast) Snackbar.make(ln, "Сайландылардан өширилди", Snackbar.LENGTH_SHORT)
                .setBackgroundTint(Color.BLACK)
                .setTextColor(Color.WHITE)
                .show()
            favoriteItem!!.setIcon(R.drawable.ic_baseline_bookmark_border_24)
        }
        toast = true
    }

}