/*
 * Copyright (C) 2021 Image Picker
 * Author: Nguyen Hoang Lam <hoanglamvn90@gmail.com>
 */

package com.thanyada.imagepickerlib.ui.camera

import com.thanyada.imagepickerlib.model.Image

interface OnImageReadyListener {
    fun onImageReady(images: ArrayList<Image>)
    fun onImageNotReady()
}