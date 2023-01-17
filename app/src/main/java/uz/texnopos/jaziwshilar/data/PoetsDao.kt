package uz.texnopos.jaziwshilar.data

import androidx.room.*

@Dao
interface PoetsDao {

    @Query("SELECT biography FROM jaziwshilar WHERE id =:id")
    fun getBiographyById(id: Int): String

    @Query("SELECT lifeSpan FROM jaziwshilar WHERE id=:id")
    fun getLifeSpanById(id: Int): String

    @Query("SELECT poetName FROM jaziwshilar WHERE id=:id")
    fun getPoetNameById(id: Int): String

    @Query("UPDATE jaziwshilar SET isFavorite=:isFavorite WHERE id=:id")
    fun setStatus(id: Int, isFavorite: Int)

    @Query("SELECT isFavorite FROM jaziwshilar WHERE id=:id")
    fun getStatus(id: Int): Int

    @Query("UPDATE jaziwshilar SET date=:currentTime WHERE id=:id")
    fun setDate(id: Int, currentTime: Long)

    @Query("SELECT id,poetName FROM jaziwshilar WHERE isFavorite=1 ORDER by date DESC")
    fun getAllFavorites(): List<Poets>

    @Query("SELECT id , poetName from jaziwshilar")
    fun getAllPoetsAndId(): List<Poets>
}
