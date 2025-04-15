package com.alisherbu.writers.biography

import com.alisherbu.writers.data.PoetsDao

class BiographyPresenter(private var dao: PoetsDao, var view: BiographyView, var id: Int) {
    private var isFavorite = dao.getStatus(id) == 1
    fun setBookmark() {
        view.changeBookmark(isFavorite)
    }

    fun getBiography() {
        val poet = dao.getPoetById(id)
        view.getBiography(
            biography = poet.biography.toString(),
            poetName = poet.poetName.toString(),
            lifeSpan = poet.lifeSpan.toString()
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
}
