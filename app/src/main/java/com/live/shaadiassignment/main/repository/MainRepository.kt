package com.live.shaadiassignment.main.repository

import com.live.shaadiassignment.api.MainApiHelper
import javax.inject.Inject

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

class MainRepository @Inject constructor(private val mainApiHelper: MainApiHelper) {

    suspend fun getProfiles(results: Int) = mainApiHelper.getProfiles(results)

}