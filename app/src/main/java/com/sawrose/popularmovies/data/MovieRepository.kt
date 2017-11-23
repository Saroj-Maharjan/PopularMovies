package com.sawrose.popularmovies.data

import com.sawrose.popularmovies.data.local.MovieDB
import com.sawrose.popularmovies.data.remote.MovieAPI
import com.sawrose.popularmovies.model.Movie
import com.sawrose.popularmovies.model.MovieDetail
import com.sawrose.popularmovies.model.MovieResponse
import com.sawrose.popularmovies.model.Video
import io.reactivex.Completable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject


class MovieRepository @Inject constructor(factory: RepositoryFactory) {
    private val movieAPI: MovieAPI = factory.createMovieAPI()
    private val movieDB: MovieDB = factory.createMovieDB()

    fun getPopMovies(page: Int): Single<List<Movie>> {
        return getMovies(movieAPI.getPopularMovies(page))
    }

    fun getTopMovies(page: Int): Single<List<Movie>> {
        return getMovies(movieAPI.getTopRatedMovies(page))
    }

    fun getFavMovies() : Single<List<Movie>>{
        return movieDB.getFavoriteMovies()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
    }

    fun saveMovie(movie: Movie): Completable {
        return movieDB.saveMovie(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
    }

    fun deleteMovie(movie: Movie): Completable {
        return movieDB.deleteMovie(movie)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
    }

    fun getMovieDetail(id: Int): Single<MovieDetail> {
        return movieAPI.getMovieDetail(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
    }

    fun getVideoKeys(id: Int): Single<List<String>> {
        return movieAPI.getVideos(id)
                .toObservable()
                .flatMapIterable<Video> { it.results }
                .flatMapSingle { video -> Single.just<String>(video.key) }
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .unsubscribeOn(Schedulers.io())
    }

    private fun getMovies(movieResponseSingle: Single<MovieResponse>): Single<List<Movie>> {
        return movieResponseSingle.flatMap { movieResponse -> Single.just<List<Movie>>(movieResponse.results) }
                .subscribeOn(
                        Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).unsubscribeOn(Schedulers.io())
    }
}