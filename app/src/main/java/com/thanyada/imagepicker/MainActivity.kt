package com.thanyada.imagepicker

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.AdapterView.OnItemClickListener
import android.widget.BaseAdapter
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import com.canhub.cropper.CropImageView
import com.thanyada.imagepicker.databinding.ActivityMainBinding
import com.thanyada.imagepickerlib.helper.GlideHelper
import com.thanyada.imagepickerlib.model.Image
import com.thanyada.imagepickerlib.model.ImagePickerConfig
import com.thanyada.imagepickerlib.model.ImagePickerMode
import com.thanyada.imagepickerlib.ui.imagepicker.registerImagePicker


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var originImages: ArrayList<Image> = arrayListOf()
    var adapter: CustomAdapter? = null

    private val launcherImagePicker = registerImagePicker { images ->
        originImages = images
//        var messageImagesPicker = ""
//        images.forEachIndexed { index, image ->
//            messageImagesPicker += "$index | ${image.uri}\n"
//        }

        adapter?.update(images)
//        binding.ImageSelectedTextView.text = messageImagesPicker
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.MoreImagesShowCameraButton.setOnClickListener {
            val config = ImagePickerConfig(
                mode = ImagePickerMode.MULTIPLE_IMAGE,
                isShowSwitchModeButton = true,
                maxSize = 5,
                limitMessage = "you choose max limit",
                selectedImages = originImages
            )

            launcherImagePicker.launch(config)
        }

        binding.MoreImagesHideCameraButton.setOnClickListener {

            val config = ImagePickerConfig(
                mode = ImagePickerMode.MULTIPLE_IMAGE,
                isShowSwitchModeButton = false,
                maxSize = 5,
                limitMessage = "you choose max limit",
                selectedImages = originImages
            )

            launcherImagePicker.launch(config)
        }

        binding.OneImageShowCameraButton.setOnClickListener {

            val config = ImagePickerConfig(
                mode = ImagePickerMode.SINGLE_IMAGE,
                isShowSwitchModeButton = true
            )

            launcherImagePicker.launch(config)
        }

        binding.OneImageWithCameraAndCropButton.setOnClickListener {
            val config = ImagePickerConfig(
                mode = ImagePickerMode.SINGLE_IMAGE,
                isShowSwitchModeButton = true,
                cropOptions = {
                    setAspectRatio(1,1)
                    setGuidelines(CropImageView.Guidelines.ON)
                }
            )

            launcherImagePicker.launch(config)
        }

        binding.OnlyCameraBackButton.setOnClickListener {

        }

        binding.OnlyCameraFrontButton.setOnClickListener {

        }

        binding.OnlyCameraCitizenCardButton.setOnClickListener {

        }

        binding.OnlyCameraCitizenCardWithOwnerButton.setOnClickListener {

        }


        if(adapter == null ){
            adapter = CustomAdapter(this)
            binding.gridView.adapter = adapter
        }

        binding.imagePreviewCloseButton.setOnClickListener {
            binding.imagePreviewRelativeLayout.isVisible = false
        }

        binding.gridView.onItemClickListener = OnItemClickListener { parent, v, position, id ->
            binding.imagePreviewRelativeLayout.isVisible = true
            GlideHelper.loadImageWithOutCenterCrop( binding.imagePreviewImageView, originImages[position].uri)

        }
    }

    class CustomAdapter(context: Context) : BaseAdapter() {
        private val mContext: Context = context
        private var originImages: ArrayList<Image> = arrayListOf()
        override fun getCount(): Int {
            return originImages.size
        }

        override fun getItem(position: Int): Image {
            return originImages[position]
        }

        override fun getItemId(position: Int): Long {
            return 0
        }

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val imageView: ImageView
            if (convertView == null) {
                imageView = ImageView(mContext)
                imageView.layoutParams = AbsListView.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, 200
                )
                imageView.scaleType = ImageView.ScaleType.FIT_XY
                imageView.setPadding(4, 4, 4, 4)
            } else {
                imageView = convertView as ImageView
            }
            GlideHelper.loadImage(imageView, getItem(position).uri)
            return imageView
        }

        fun update(images: java.util.ArrayList<Image>) {
            this.originImages = images
            notifyDataSetChanged()
        }

    }
}

