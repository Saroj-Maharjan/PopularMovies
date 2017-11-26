package com.sawrose.popularmovies.data.local

import com.sawrose.popularmovies.model.Movie
import io.reactivex.Completable
import io.reactivex.Single


class MovieDB(popMoviesDB: PopMoviesDB) {

    private var movieDao: MovieDao = popMoviesDB.movieDao()

    fun saveMovie(movie: Movie): Completable {
        return Completable.create { completableEmitter ->
            movieDao.insert(movie)
            completableEmitter.onComplete()
        }
    }

    fun deleteMovie(movie: Movie): Completable {
        return Completable.create { completableEmitter ->
            movieDao.delete(movie)
            completableEmitter.onComplete()
        }
    }

    fun getFavoriteMovies(): Single<List<Movie>> = movieDao.getFavoriteMovies().onBackpressureBuffer().firstOrError()

}