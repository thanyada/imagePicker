/*
 * Copyright (C) 2021 Image Picker
 * Author: Nguyen Hoang Lam <hoanglamvn90@gmail.com>
 */

package com.thanyada.imagepickerlib.model

import android.content.Context
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Environment
import android.os.Parcelable
import com.canhub.cropper.CropImageContractOptions
import com.thanyada.imagepickerlib.R
import kotlinx.parcelize.Parcelize
import java.util.*

enum class RootDirectory(val value: String) {
    DCIM(Environment.DIRECTORY_DCIM),
    PICTURES(Environment.DIRECTORY_PICTURES),
    DOWNLOADS(Environment.DIRECTORY_DOWNLOADS)
}

@Parcelize
class ImagePickerConfig(
    var statusBarColor: String = "#ED1C24",
    var toolbarColor: String = "#ED1C24",
    var isLightStatusBar: Boolean = false,
    var toolbarTextColor: String = "#FFFFFF",
    var toolbarIconColor: String = "#FFFFFF",
    var backgroundColor: String = "#424242",
    var progressIndicatorColor: String = "#ED1C24",
    var selectedIndicatorColor: String = "#ED1C24",
    var mode: ImagePickerMode = ImagePickerMode.SINGLE_IMAGE,
    var cameraMode: ImagePickerCameraMode = ImagePickerCameraMode.COMMON_BACK,
    var isShowSwitchModeButton: Boolean = false,
    var cropOptions: (CropImageContractOptions.() -> (Unit))? = null,
    var isFolderMode: Boolean = true,
    var isShowNumberIndicator: Boolean = false,
    var maxSize: Int = Int.MAX_VALUE,
    var folderGridCount: GridCount = GridCount(2, 4),
    var imageGridCount: GridCount = GridCount(3, 5),
    var doneTitle: String? = null,
    var folderTitle: String? = null,
    var imageTitle: String? = null,
    var limitMessage: String? = null,
    var rootDirectory: RootDirectory = RootDirectory.DCIM,
    var subDirectory: String? = null,
    var isAlwaysShowDoneButton: Boolean = false,
    var selectedImages: ArrayList<Image> = arrayListOf()
) : Parcelable {


    fun initDefaultValues(context: Context) {
        val resource = context.resources
        if (folderTitle == null) {
            folderTitle = resource.getString(R.string.image_picker_title_folder)
        }
        if (imageTitle == null) {
            imageTitle = resource.getString(R.string.image_picker_title_image)
        }
        if (doneTitle == null) {
            doneTitle = resource.getString(R.string.image_picker_action_done)
        }
        if (subDirectory == null) {
            subDirectory = getDefaultSubDirectoryName(context)
        }
    }

    private fun getDefaultSubDirectoryName(context: Context): String {
        val pm = context.packageManager
        val ai: ApplicationInfo? = try {
            pm.getApplicationInfo(context.applicationContext.packageName ?: "", 0)
        } catch (e: PackageManager.NameNotFoundException) {
            null
        }
        return (if (ai != null) pm.getApplicationLabel(ai) else "Camera") as String
    }
}