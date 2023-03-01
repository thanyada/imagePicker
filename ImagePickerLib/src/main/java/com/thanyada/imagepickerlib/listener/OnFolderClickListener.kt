/*
 * Copyright (C) 2021 Image Picker
 * Author: Nguyen Hoang Lam <hoanglamvn90@gmail.com>
 */

package com.thanyada.imagepickerlib.listener

import com.thanyada.imagepickerlib.model.Folder

interface OnFolderClickListener {
    fun onFolderClick(folder: Folder)
}