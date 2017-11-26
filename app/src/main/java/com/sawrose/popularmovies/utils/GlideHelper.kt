package com.sawrose.popularmovies.utils

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.FitCenter
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.RequestOptions.fitCenterTransform
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.sawrose.popularmovies.utils.Constants.MOVIES_IMG_URL
import com.sawrose.popularmovies.utils.Constants.YOUTUBE_IMG_URL


class GlideHelper {
    companion object {
        fun loadThumbnail(context: Context, videoId: String, imageView: ImageView) {
            Glide.with(context)
                    .load(String.format(YOUTUBE_IMG_URL, videoId))
                    .apply(RequestOptions()
                            .centerCrop())
                    .into(imageView)
        }

        fun loadPoster(context: Context, posterPath: String, imageView: ImageView) {
            Glide.with(context)
                    .load(String.format(MOVIES_IMG_URL, "w342", posterPath))
                    .apply(RequestOptions()
                            .centerCrop()
                            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                            .dontAnimate()
                            .skipMemoryCache(true)
                    )
                    .into(imageView)
        }

        fun loadBackdrop(context: Context, backdropPath: String, imageView: ImageView) {
            Glide.with(context)
                    .load(String.format(MOVIES_IMG_URL, "w780", backdropPath))
                    .apply(fitCenterTransform())
                    .into(imageView)
        }
    }
}