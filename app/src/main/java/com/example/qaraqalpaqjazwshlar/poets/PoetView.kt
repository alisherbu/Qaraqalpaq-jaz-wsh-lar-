package com.example.qaraqalpaqjazwshlar.poets

import com.example.qaraqalpaqjazwshlar.data.Poets

interface PoetView {
    fun setData(models:List<Poets>)
    fun filteredNames(list: List<Poets>)
}