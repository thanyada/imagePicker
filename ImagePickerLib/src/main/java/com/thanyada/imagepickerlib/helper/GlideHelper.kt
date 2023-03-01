/*
 * Copyright (C) 2021 Image Picker
 * Author: Nguyen Hoang Lam <hoanglamvn90@gmail.com>
 */

package com.thanyada.imagepickerlib.helper

import android.net.Uri
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.bumptech.glide.request.RequestOptions
import com.thanyada.imagepickerlib.R

class GlideHelper {

    companion object {
        private val options: RequestOptions =
            RequestOptions().placeholder(R.drawable.imagepicker_image_placeholder)
                .error(R.drawable.imagepicker_image_error)
                .centerCrop()

        fun loadImage(imageView: ImageView, uri: Uri) {
            Glide.with(imageView.context)
                .load(uri)
                .apply(options)
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)

        }

        fun loadImageWithOutCenterCrop(imageView: ImageView, uri: Uri) {
            Glide.with(imageView.context)
                .load(uri)
                .apply( RequestOptions().placeholder(R.drawable.imagepicker_image_placeholder)
                    .error(R.drawable.imagepicker_image_error))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(imageView)

        }
    }
}