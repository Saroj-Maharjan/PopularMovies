package com.sawrose.popularmovies.di.module

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.content.Context
import android.graphics.Movie
import com.sawrose.popularmovies.di.ViewModelFactory
import com.sawrose.popularmovies.di.component.ViewModelSubComponent
import com.sawrose.popularmovies.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 * Created by Apps on 11/23/2017.
 */
@Module(subcomponents = arrayOf(ViewModelSubComponent::class))
class AppModule(internal val application:Application) {

    @Provides
    @Singleton
    internal fun provideApplication(): Application {
        return application
    }

    @Provides
    @Singleton
    internal fun provideContext():Context{
        return application.applicationContext
    }

//    @Singleton
//    @Provides
//    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
//
//        return Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .client(okHttpClient)
//                .addConverterFactory(MoshiConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .build()
//    }

//    @Singleton
//    @Provides
//    internal fun providesMovieSubject(): PublishSubject<Movie> {
//        return PublishSubject.create<Movie>()
//    }
//
//    @Singleton
//    @Provides
//    internal fun provideDb(): PopMoviesDB {
//        return Room.databaseBuilder(application, PopMoviesDB::class.java, "movie.db").build()
//    }

    @Singleton
    @Provides
    internal fun provideViewModelFactory(
            viewModelSubComponent: ViewModelSubComponent.Builder): ViewModelProvider.Factory {
        return ViewModelFactory(viewModelSubComponent.build())
    }
}