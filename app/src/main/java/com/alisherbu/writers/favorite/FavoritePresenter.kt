package com.alisherbu.writers.favorite

import com.alisherbu.writers.data.PoetsDao

class FavoritePresenter(private var dao: PoetsDao, var view: FavoriteView) {

    fun getAllFavorites() {
        view.setData(dao.getAllFavorites())
    }
}
