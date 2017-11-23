package com.sawrose.popularmovies.di.module

import android.app.Application
import android.arch.lifecycle.ViewModelProvider
import android.arch.persistence.room.Room
import android.content.Context
import android.graphics.Movie
import com.readystatesoftware.chuck.ChuckInterceptor
import com.sawrose.popularmovies.BuildConfig
import com.sawrose.popularmovies.data.local.PopMoviesDB
import com.sawrose.popularmovies.di.ViewModelFactory
import com.sawrose.popularmovies.di.component.ViewModelSubComponent
import com.sawrose.popularmovies.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import io.reactivex.subjects.PublishSubject
import okhttp3.Cache
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit
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

    @Provides
    @Singleton
    internal fun provideOkHttpClient(): OkHttpClient {
        val networkInterceptor = { chain: Interceptor.Chain ->
            val original = chain.request()
            val originalHttpUrl = original.url()

            val url = originalHttpUrl.newBuilder()
                    .addQueryParameter("api_key", BuildConfig.MOVIE_DB_API_KEY)
                    .build()

            val requestBuilder = original.newBuilder().url(url)

            val request = requestBuilder.build()
            chain.proceed(request)
        }
        val loggingInterceptor = HttpLoggingInterceptor { message -> Timber.tag("OkHttp").d(message) }
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC

        val cacheSize = 10 * 1024 * 1024
        val cache = Cache(application.cacheDir, cacheSize.toLong())

        return OkHttpClient.Builder()
                .addInterceptor(networkInterceptor)
                .addInterceptor(loggingInterceptor)
                .addInterceptor(ChuckInterceptor(application))
                .cache(cache)
                .connectTimeout(40, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)
                .build()
    }

    @Singleton
    @Provides
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {

        return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(MoshiConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
    }

    @Singleton
    @Provides
    internal fun providesMovieSubject(): PublishSubject<Movie> {
        return PublishSubject.create<Movie>()
    }

    @Singleton
    @Provides
    internal fun provideDb(): PopMoviesDB {
        return Room.databaseBuilder(application, PopMoviesDB::class.java, "movie.db").build()
    }

    @Singleton
    @Provides
    internal fun provideViewModelFactory(
            viewModelSubComponent: ViewModelSubComponent.Builder): ViewModelProvider.Factory {
        return ViewModelFactory(viewModelSubComponent.build())
    }
}