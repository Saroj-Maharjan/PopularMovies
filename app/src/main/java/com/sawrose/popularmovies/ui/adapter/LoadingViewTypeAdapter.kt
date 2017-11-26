package com.sawrose.popularmovies.ui.adapter

import android.support.v7.widget.RecyclerView
import android.support.v7.widget.RecyclerView.ViewHolder
import android.view.View
import android.view.ViewGroup
import com.sawrose.popularmovies.R
import com.sawrose.popularmovies.base.ViewType
import com.sawrose.popularmovies.base.ViewTypeAdapter
import com.sawrose.popularmovies.utils.inflate


class LoadingViewTypeAdapter : ViewTypeAdapter {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        return LoadingViewHolder(parent.inflate(R.layout.view_loader))
    }

    override fun onBindViewHolder(holder: ViewHolder, item: ViewType) {
    }

    class LoadingViewHolder(itemView: View) : ViewHolder(itemView)
}