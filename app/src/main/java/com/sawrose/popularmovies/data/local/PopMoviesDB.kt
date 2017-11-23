package com.sawrose.popularmovies.data.local

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.sawrose.popularmovies.model.Movie


@Database(entities = arrayOf(Movie::class), version = 1,exportSchema = false)
abstract class PopMoviesDB : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}