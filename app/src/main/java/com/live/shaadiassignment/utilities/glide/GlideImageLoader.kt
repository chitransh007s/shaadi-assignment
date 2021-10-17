package com.live.shaadiassignment.utilities.glide

import android.content.Context
import android.widget.ImageView
import com.live.shaadiassignment.utilities.glide.ImageLoad
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

class GlideImageLoader(context: Context) : ImageLoad {

    private val requestManager: RequestManager = Glide.with(context).applyDefaultRequestOptions(RequestOptions.timeoutOf(5 * 60 * 1000))
    private val diskCaching: RequestOptions = RequestOptions.diskCacheStrategyOf(DiskCacheStrategy.ALL)

    override fun loadCircleImage(url: String?, thumbnailUrl: String?, imageView: ImageView?) {
        requestManager.load(url)
            .thumbnail(requestManager.load(thumbnailUrl)
                .apply(RequestOptions.circleCropTransform())
                .apply(diskCaching))
            .apply(RequestOptions.circleCropTransform())
            .apply(diskCaching)
            .into(imageView!!)
    }

    override fun clearImage(imageView: ImageView?) {
        requestManager.clear(imageView!!)
    }

}