package com.live.shaadiassignment.api

import com.live.shaadiassignment.main.models.ProfileResponseModel
import retrofit2.Response

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

interface MainApiHelper {

    suspend fun getProfiles(
        results: Int
    ) : Response<ProfileResponseModel>?

}