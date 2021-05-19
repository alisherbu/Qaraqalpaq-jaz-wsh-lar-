package uz.texnopos.jaziwshilar.data

import androidx.room.*

@Entity(tableName = "jaziwshilar")
data class Poets(
    @PrimaryKey val id: Int,
    @ColumnInfo(name = "poetName")
    val poetName: String?,
    @ColumnInfo(name = "lifeSpan")
    val lifeSpan: String?,
    @ColumnInfo(name = "biography")
    val biography: String?,
    @ColumnInfo(name = "isFavorite")
    val isFavorite: Int?,
    @ColumnInfo(name = "date")
    val date: Int?
)
