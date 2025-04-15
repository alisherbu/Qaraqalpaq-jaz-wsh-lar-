package com.alisherbu.writers.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [PoetEntity::class], version = 1)
abstract class PoetsDatabase : RoomDatabase() {
    companion object {
        private lateinit var INSTANCE: PoetsDatabase
        fun getInstance(context: Context): PoetsDatabase {
            if (!Companion::INSTANCE.isInitialized) {
                INSTANCE = Room.databaseBuilder(
                    context,
                    PoetsDatabase::class.java,
                    "poets.db"
                )
                    .allowMainThreadQueries()
                    .createFromAsset("poets.db")
                    .build()
            }
            return INSTANCE
        }
    }

    abstract fun dao(): PoetsDao
}
