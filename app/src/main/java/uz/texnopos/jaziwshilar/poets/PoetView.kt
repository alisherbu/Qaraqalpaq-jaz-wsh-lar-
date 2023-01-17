package uz.texnopos.jaziwshilar.poets

import uz.texnopos.jaziwshilar.data.Poets

interface PoetView {
    fun setData(models: List<Poets>)
    fun filteredNames(list: List<Poets>)
}
