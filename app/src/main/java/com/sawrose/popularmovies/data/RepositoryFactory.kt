package com.sawrose.popularmovies.data

import com.sawrose.popularmovies.data.local.MovieDB
import com.sawrose.popularmovies.data.local.PopMoviesDB
import com.sawrose.popularmovies.data.remote.MovieAPI
import retrofit2.Retrofit
import javax.inject.Inject


class RepositoryFactory @Inject constructor(
        private val retrofit: Retrofit,
        private val popMoviesDB: PopMoviesDB
) {

    fun createMovieAPI(): MovieAPI {
        return MovieAPI(retrofit)
    }

    fun createMovieDB(): MovieDB {
        return MovieDB(popMoviesDB)
    }

}