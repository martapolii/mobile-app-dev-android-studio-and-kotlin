package com.example.martapolishchuk_comp304_401_lab03_exercise02.data

// Marta Polishchuk - 301432299
// Assignment 3: Exercise 2

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room3.Room
import androidx.room3.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


// Room database containing the movies table
@Database(entities = [Movie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao // Provides access to MovieDao

    companion object {
        @Volatile
        private var Instance: MovieDatabase? = null

        // Singleton pattern to get or create the database instance
        fun getDatabase(context: Context): MovieDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_db"
                )
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // Prepopulate database with sample data on first creation
                            CoroutineScope(Dispatchers.IO).launch {
                                getDatabase(context).movieDao().insertSampleMovies(
                                    listOf( // 3 sample movies
                                        Movie(
                                            id = 101,
                                            title = "Inception",
                                            director = "Christopher Nolan",
                                            price = 19.99,
                                            releaseDate = "2010-07-16",
                                            duration = 148,
                                            genre = "Action",
                                            isFavorite = true
                                        ),
                                        Movie(
                                            id = 102,
                                            title = "Toy Story",
                                            director = "John Lasseter",
                                            price = 14.99,
                                            releaseDate = "1995-11-22",
                                            duration = 81,
                                            genre = "Family",
                                            isFavorite = false
                                        ),

                                        Movie(
                                            id = 103,
                                            title = "Knives Out",
                                            director = "Rian Johnson",
                                            price = 17.99,
                                            releaseDate = "2019-11-27",
                                            duration = 130,
                                            genre = "Comedy",
                                            isFavorite = true
                                        )
                                    )
                                )
                            }
                        }
                    })//call back object
                    .build()
                    .also { Instance = it }
            }
        }

    }
}