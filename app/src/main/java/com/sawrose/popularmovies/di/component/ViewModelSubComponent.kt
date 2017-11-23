package com.sawrose.popularmovies.di.component

import com.sawrose.popularmovies.ui.viewmodel.MovieDetailsViewModel
import com.sawrose.popularmovies.ui.viewmodel.MoviesViewModel
import dagger.Subcomponent


@Subcomponent
interface ViewModelSubComponent {

    fun moviesViewModel(): MoviesViewModel

    fun movieDetailsViewModel(): MovieDetailsViewModel

    @Subcomponent.Builder interface Builder {
        fun build(): ViewModelSubComponent
    }
}