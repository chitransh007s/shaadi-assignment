package com.live.shaadiassignment.main.models

import com.google.gson.annotations.SerializedName
import com.live.shaadiassignment.utilities.network.DefaultResponseModel

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

open class ProfileResponseModel {

    @SerializedName("results")
    var profileList : List<ProfileModel> = ArrayList()

    @SerializedName("info")
    var defaultResponseModel : DefaultResponseModel = DefaultResponseModel()

}