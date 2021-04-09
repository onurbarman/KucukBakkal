package com.app.kucukbakkalapp.data.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Basket::class],
    version = 1
)
abstract class BasketDatabase : RoomDatabase(){

    abstract fun getBasketDao() : BasketDao

    companion object {

        @Volatile private var instance : BasketDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK){
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            BasketDatabase::class.java,
            "basketdb"
        ).build()

    }
}