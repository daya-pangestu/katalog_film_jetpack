package com.daya.katalogfilm.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.daya.katalogfilm.model.favorite.MovieFavModel
import com.daya.katalogfilm.model.favorite.TvShowFavModel

@Database(
    entities = [MovieFavModel::class, TvShowFavModel::class],
    version = 3,
    exportSchema = false
)

abstract class KatalogDatabase : RoomDatabase() {

    abstract fun favDao() :FavoriteDao


    companion object{
        private var INSTANCE: KatalogDatabase? = null

        fun getInstance(context: Context) : KatalogDatabase {
            if (INSTANCE == null) {
                synchronized(KatalogDatabase::class) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        KatalogDatabase::class.java, "katalogfavorite"
                    )
                        .allowMainThreadQueries()

                        .fallbackToDestructiveMigrationFrom()
                        .build()
                }
            }
            return INSTANCE!!
        }

        fun roomInMeMory(context: Context): KatalogDatabase {
            return Room.inMemoryDatabaseBuilder(
                context,
                KatalogDatabase::class.java)
                .allowMainThreadQueries()
                .build()
        }
    }
}