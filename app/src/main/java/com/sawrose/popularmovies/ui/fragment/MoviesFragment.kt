package com.sawrose.popularmovies.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sawrose.popularmovies.R
import com.sawrose.popularmovies.base.BaseFragment
import com.sawrose.popularmovies.di.Injectable
import com.sawrose.popularmovies.utils.inflate


class MoviesFragment: BaseFragment(),Injectable {

    private var pageNumber = 1
    private var position: Int = 0

    companion object {
        private val POSITION = "position"
        private val LAYOUT_MANAGER_STATE = "state"
        val POSITION_POPULAR = 0
        val POSITION_RATED = 1
        val POSITION_FAVORITE = 2

        fun newInstance(position: Int): MoviesFragment {
            val fragment = MoviesFragment()
            val bundle = Bundle()
            bundle.putInt(POSITION, position)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = container?.inflate(R.layout.fragment_movies, false)
        position = arguments.getInt(POSITION)
        return view
    }
}