package com.alisherbu.writers.favorite

import com.alisherbu.writers.data.PoetEntity

interface FavoriteView {
    fun setData(models: List<PoetEntity>)
}
