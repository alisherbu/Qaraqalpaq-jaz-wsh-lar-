package uz.texnopos.jaziwshilar.favorite

import uz.texnopos.jaziwshilar.data.PoetEntity

interface FavoriteView {
    fun setData(models: List<PoetEntity>)
}
