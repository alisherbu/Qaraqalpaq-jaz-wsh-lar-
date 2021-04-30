package com.example.qaraqalpaqjazwshlar.favorite

import com.example.qaraqalpaqjazwshlar.data.Poets
import com.example.qaraqalpaqjazwshlar.data.PoetsDao

interface FavoriteView {
    fun setData(models:List<Poets>)
    fun startActivity(dao:PoetsDao)
}