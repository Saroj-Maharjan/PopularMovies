package com.sawrose.popularmovies.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.sawrose.popularmovies.R
import com.sawrose.popularmovies.ui.activity.MainActivity
import com.sawrose.popularmovies.utils.inflate
import kotlinx.android.synthetic.main.fragment_pager.*
import kotlinx.android.synthetic.main.toolbar.*


class MoviePagerFragment :Fragment() {

    private lateinit var adapter: PagerAdapter

    companion object {
        private val PAGE_GOUNT = 3
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return container?.inflate(R.layout.fragment_pager, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PagerAdapter(fragmentManager)
        viewpager.offscreenPageLimit = PAGE_GOUNT - 1
        viewpager.adapter = adapter
        tabs.setViewPager(viewpager)
        (activity as MainActivity).setSupportActionBar(toolbar)

    }

    internal inner class PagerAdapter(fm:FragmentManager):FragmentPagerAdapter(fm){
        override fun getItem(position: Int): Fragment {
            return MoviesFragment.newInstance(position)
        }

        override fun getCount(): Int {
            return PAGE_GOUNT
        }

        override fun getPageTitle(position: Int): CharSequence {
            return when (position) {
                MoviesFragment.POSITION_POPULAR -> context.getString(R.string.popular)
                MoviesFragment.POSITION_RATED -> context.getString(R.string.sort_rated)
                else -> context.getString(R.string.favorites)
            }
        }
    }
}