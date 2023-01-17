package uz.texnopos.jaziwshilar.favorite

import uz.texnopos.jaziwshilar.data.PoetsDao

class FavoritePresenter(private var dao: PoetsDao, var view: FavoriteView) {

    fun getAllFavorites() {
        view.setData(dao.getAllFavorites())
    }
}
