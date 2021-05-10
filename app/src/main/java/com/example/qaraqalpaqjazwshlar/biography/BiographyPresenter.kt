package com.example.qaraqalpaqjazwshlar.biography

import android.os.Build
import android.text.Html
import android.widget.Toast
import com.example.qaraqalpaqjazwshlar.data.PoetsDatabase


@Suppress("DEPRECATION")
class BiographyPresenter(var activity: BioActivity, var view: BiographyView, var id: Int) {
    private val dao = PoetsDatabase.getInstance(activity).dao()
    private var isFavorite = dao.getStatus(id) == 1
    fun setBookmark() {
        view.changeBookmark(isFavorite)
    }

    fun getBiography() {
        val biography = dao.getBiographyById(id)
        view.getBiography(
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    Html.fromHtml(biography, Html.FROM_HTML_MODE_COMPACT)
                } else {
                    Html.fromHtml(biography)
                }.toString(),
                dao.getPoetNameById(id),
                dao.getLifeSpanById(id)
        )
    }

    fun changeBookmark() {
        isFavorite = !isFavorite
        if (isFavorite) {
            Toast.makeText(activity,"Saylandılarǵa qosıldı",Toast.LENGTH_SHORT).show()
            dao.setStatus(id, 1)
            dao.setDate(id, System.currentTimeMillis())
        } else {
            Toast.makeText(activity,"Saylandılardan óshirildi",Toast.LENGTH_SHORT).show()
            dao.setStatus(id, 0)
        }
        view.changeBookmark(isFavorite)
    }

    fun share(text: CharSequence) {
        view.share(text)
    }
}