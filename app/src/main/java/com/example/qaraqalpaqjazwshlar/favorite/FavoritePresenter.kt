package com.example.qaraqalpaqjazwshlar.favorite

import com.example.qaraqalpaqjazwshlar.data.PoetsDao
import com.example.qaraqalpaqjazwshlar.data.PoetsDatabase

class FavoritePresenter(fragment: FragmentFavorite, var view: FavoriteView) {
    private var dao: PoetsDao = PoetsDatabase.getInstance(fragment.requireContext()).dao()

    fun getAllFavorites() {
        view.setData(dao.getAllFavorites())
    }

    fun startActivity() {
        view.startActivity(dao)
    }
}