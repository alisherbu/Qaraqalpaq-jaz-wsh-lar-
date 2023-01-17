package uz.texnopos.jaziwshilar.data

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
                    "poets12.db"
                )
                    .allowMainThreadQueries()
                    .createFromAsset("poets12.db")
                    .build()
            }
            return INSTANCE
        }
    }

    abstract fun dao(): PoetsDao
}
