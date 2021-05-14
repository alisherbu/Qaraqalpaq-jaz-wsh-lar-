package com.example.qaraqalpaqjazwshlar.favorite

import com.example.qaraqalpaqjazwshlar.data.PoetsDao
import com.example.qaraqalpaqjazwshlar.data.PoetsDatabase

class FavoritePresenter(private var dao:PoetsDao, var view: FavoriteView) {

    fun getAllFavorites() {
        view.setData(dao.getAllFavorites())
    }

}