package com.example.qaraqalpaqjazwshlar.data

import androidx.room.Dao
import androidx.room.Query

@Dao
interface PoetsDao {
    @Query("SELECT poetName FROM jaziwshilar")
    fun getAllPoets(): List<String>

    @Query("SELECT biography FROM jaziwshilar WHERE id =:id")
    fun getBiographyById(id: Int): String

    @Query("SELECT id FROM jaziwshilar WHERE poetName=:poetName")
    fun getIdByPoetName(poetName:String):Int

    @Query("SELECT lifeSpan FROM jaziwshilar WHERE id=:id")
    fun getLifeSpanById(id:Int):String

    @Query("SELECT poetName FROM jaziwshilar WHERE id=:id")
    fun getPoetNameById(id:Int):String
}