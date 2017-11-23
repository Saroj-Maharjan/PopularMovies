package com.sawrose.popularmovies.di.component

import android.app.Application
import com.sawrose.popularmovies.MovieApp
import com.sawrose.popularmovies.di.module.ActivitiesModule
import com.sawrose.popularmovies.di.module.AppModule
import com.sawrose.popularmovies.di.module.FragmentBuildersModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AndroidInjectionModule::class,AppModule::class,ActivitiesModule::class))
interface AppComponent {

    fun inject(application:MovieApp)

    @Component.Builder
    interface Builder{
        @BindsInstance
        fun application(application: Application) : Builder
        fun appModule(appModule: AppModule) : Builder
        fun build() : AppComponent
    }
}