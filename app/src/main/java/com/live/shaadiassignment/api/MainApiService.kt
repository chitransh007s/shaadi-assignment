package com.live.shaadiassignment.api

import com.live.shaadiassignment.main.models.ProfileResponseModel
import com.live.shaadiassignment.utilities.network.Urls
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

interface MainApiService {

    @GET(Urls.BASE_URL)
    suspend fun getProfiles(
        @Query("results") results: Int): Response<ProfileResponseModel>

}