package com.sawrose.popularmovies.di.module

import com.sawrose.popularmovies.ui.fragment.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun moviesFragment(): MoviesFragment

}