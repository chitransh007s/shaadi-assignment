package com.live.shaadiassignment.utilities.glide

import android.widget.ImageView

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */
interface ImageLoad {
    fun loadCircleImage(url: String?, thumbnailUrl: String?, imageView: ImageView?)
    fun clearImage(imageView: ImageView?)
}