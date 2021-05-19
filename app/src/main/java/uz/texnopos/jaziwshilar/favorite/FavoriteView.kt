package uz.texnopos.jaziwshilar.favorite

import uz.texnopos.jaziwshilar.data.Poets

interface FavoriteView {
    fun setData(models: List<Poets>)
}