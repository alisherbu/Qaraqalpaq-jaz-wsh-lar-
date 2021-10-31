package uz.texnopos.jaziwshilar.biography

import uz.texnopos.jaziwshilar.data.PoetsDao

class BiographyPresenter(private var dao: PoetsDao, var view: BiographyView, var id: Int) {
    private var isFavorite = dao.getStatus(id) == 1
    fun setBookmark() {
        view.changeBookmark(isFavorite)
    }

    fun getBiography() {
        view.getBiography(
            dao.getBiographyById(id),
            dao.getPoetNameById(id),
            dao.getLifeSpanById(id)
        )
    }

    fun  changeBookmark() {
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
