package com.live.shaadiassignment.api

import com.live.shaadiassignment.main.models.ProfileResponseModel
import retrofit2.Response
import javax.inject.Inject

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

class MainApiHelperImpl @Inject constructor(
        private val apiService: MainApiService
) : MainApiHelper {

    override suspend fun getProfiles(results: Int): Response<ProfileResponseModel> = apiService.getProfiles(results)

}