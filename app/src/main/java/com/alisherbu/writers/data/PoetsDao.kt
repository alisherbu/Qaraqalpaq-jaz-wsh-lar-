package com.alisherbu.writers.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PoetsDao {
    @Query("UPDATE jaziwshilar SET isFavorite=:isFavorite WHERE id=:id")
    fun setStatus(id: Int, isFavorite: Int)

    @Query("SELECT isFavorite FROM jaziwshilar WHERE id=:id")
    fun getStatus(id: Int): Int

    @Query("UPDATE jaziwshilar SET date=:currentTime WHERE id=:id")
    fun setDate(id: Int, currentTime: Long)

    @Query("SELECT * FROM jaziwshilar WHERE isFavorite=1 ORDER by date DESC")
    fun getAllFavorites(): List<PoetEntity>

    @Query("SELECT * from jaziwshilar")
    fun getAllPoets(): List<PoetEntity>

    @Query("SELECT * FROM jaziwshilar WHERE id=:id")
    fun getPoetById(id: Int): PoetEntity
}
