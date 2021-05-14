package com.example.qaraqalpaqjazwshlar.biography

import com.example.qaraqalpaqjazwshlar.data.PoetsDao

class BiographyPresenter(private var dao:PoetsDao, var view: BiographyView, var id: Int) {
    private var isFavorite = dao.getStatus(id) == 1
    fun setBookmark() {
        view.changeBookmark(isFavorite)
    }

    fun getBiography() {
        val biography = dao.getBiographyById(id)
        view.getBiography(
               biography,
                dao.getPoetNameById(id),
                dao.getLifeSpanById(id)
        )
    }

    fun changeBookmark() {
        isFavorite = !isFavorite
        if (isFavorite) {
            dao.setStatus(id, 1)
            dao.setDate(id, System.currentTimeMillis())
        } else {
            dao.setStatus(id, 0)
        }
        view.changeBookmark(isFavorite)
    }

    fun share(text: CharSequence) {
        view.share(text)
    }
}