package uz.texnopos.jaziwshilar.poets

import uz.texnopos.jaziwshilar.data.PoetEntity

interface PoetView {
    fun setData(models: List<PoetEntity>)
    fun filteredNames(list: List<PoetEntity>)
}
