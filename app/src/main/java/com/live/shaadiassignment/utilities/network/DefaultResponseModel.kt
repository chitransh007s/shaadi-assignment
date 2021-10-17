package com.live.shaadiassignment.utilities.network

import com.google.gson.annotations.SerializedName

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

open class DefaultResponseModel {

    @SerializedName("seed")
    var seed: String = ""

    @SerializedName("results")
    var results: Int = 0

    @SerializedName("version")
    var version: String = ""

    @SerializedName("page")
    var page: Int = 0

}