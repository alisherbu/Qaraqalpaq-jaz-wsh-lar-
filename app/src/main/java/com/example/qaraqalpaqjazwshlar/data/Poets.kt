package com.example.qaraqalpaqjazwshlar.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jaziwshilar")
data class Poets(
@PrimaryKey val id:Int,
@ColumnInfo(name="poetName")
val poetName:String?,
@ColumnInfo(name="lifeSpan")
val lifeSpan:String?,
@ColumnInfo(name="biography")
val biography:String?
)