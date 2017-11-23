package com.sawrose.popularmovies.di

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.support.v4.util.ArrayMap
import com.sawrose.popularmovies.di.component.ViewModelSubComponent
import com.sawrose.popularmovies.ui.viewmodel.MoviesViewModel
import java.util.concurrent.Callable
import javax.inject.Inject
import javax.inject.Singleton


@Suppress("UNCHECKED_CAST")
@Singleton
class ViewModelFactory @Inject constructor(viewModelSubComponent: ViewModelSubComponent): ViewModelProvider.Factory {
    private val creators: ArrayMap<Class<*>, Callable<out ViewModel>> = ArrayMap()

    init {
        creators.put(MoviesViewModel::class.java, Callable<ViewModel> { viewModelSubComponent.moviesViewModel() })
//        creators.put(MovieDetailsViewModel::class.java,
//                Callable<ViewModel> { viewModelSubComponent.movieDetailsViewModel() })
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        var creator: Callable<out ViewModel>? = creators[modelClass]
        if (creator == null) {
            for ((key, value) in creators) {
                if (modelClass.isAssignableFrom(key)) {
                    creator = value
                    break
                }
            }
        }
        if (creator == null) {
            throw IllegalArgumentException("unknown model class " + modelClass)
        }
        try {
            return creator.call() as T
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}