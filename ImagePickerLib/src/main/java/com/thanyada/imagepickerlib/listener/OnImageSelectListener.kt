/*
 * Copyright (C) 2021 Image Picker
 * Author: Nguyen Hoang Lam <hoanglamvn90@gmail.com>
 */

package com.thanyada.imagepickerlib.listener

import com.thanyada.imagepickerlib.model.Image

interface OnImageSelectListener {
    fun onSelectedImagesChanged(selectedImages: ArrayList<Image>)
    fun onSingleModeImageSelected(image: Image)
}