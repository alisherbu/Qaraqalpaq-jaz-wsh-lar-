package com.example.qaraqalpaqjazwshlar.data

import androidx.room.Dao
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface PoetsDao {
    @Query("SELECT poetName FROM jaziwshilar")
    fun getAllPoets(): List<String>

    @Query("SELECT biography FROM jaziwshilar WHERE id =:id")
    fun getBiographyById(id: Int): String

    @Query("SELECT id FROM jaziwshilar WHERE poetName=:poetName")
    fun getIdByPoetName(poetName: String):Int

    @Query("SELECT lifeSpan FROM jaziwshilar WHERE id=:id")
    fun getLifeSpanById(id: Int):String

    @Query("SELECT poetName FROM jaziwshilar WHERE id=:id")
    fun getPoetNameById(id: Int):String

    @Query("UPDATE jaziwshilar SET isFavorite=:isFavorite WHERE id=:id")
    fun setStatus(id: Int, isFavorite:Int)

    @Query("SELECT isFavorite FROM jaziwshilar WHERE id=:id")
    fun getStatus(id: Int):Int

    @Query("UPDATE jaziwshilar SET date=:currentTime WHERE id=:id")
    fun setDate(id: Int,currentTime:Long)

    @Query("SELECT poetName FROM jaziwshilar WHERE isFavorite=1 ORDER by date DESC")
    fun getAllFavorites():List<String>
}