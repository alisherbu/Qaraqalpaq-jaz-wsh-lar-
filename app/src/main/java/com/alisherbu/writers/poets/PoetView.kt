package com.alisherbu.writers.poets

import com.alisherbu.writers.data.PoetEntity

interface PoetView {
    fun setData(models: List<PoetEntity>)
    fun filteredNames(list: List<PoetEntity>)
}
