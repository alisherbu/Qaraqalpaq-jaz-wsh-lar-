package com.example.qaraqalpaqjazwshlar.data

import android.content.Context
import androidx.room.Dao
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Poets::class], version = 1)
abstract class PoetsDatabase : RoomDatabase() {
    companion object {
        private lateinit var INSTANCE: PoetsDatabase
        fun getInstance(context: Context): PoetsDatabase {
            if (!::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                        context,
                        PoetsDatabase::class.java, "poets2.db"
                )
                        .allowMainThreadQueries()
                        .createFromAsset("poets2.db")
                        .build()
            }
            return INSTANCE
        }
    }

    abstract fun dao(): PoetsDao
}