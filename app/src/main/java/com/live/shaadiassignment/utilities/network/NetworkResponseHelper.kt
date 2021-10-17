package com.live.shaadiassignment.utilities.network

import android.app.Activity
import android.view.View
import android.widget.Toast
import com.live.shaadiassignment.utilities.helpers.ViewUtils.showToast

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

object NetworkResponseHelper {
    fun handleResponse(activity: Activity?, view: View?, message: String?, statusCode: Int) {
        when (statusCode) {
            500 -> showToast(activity, "Internal Server Error", Toast.LENGTH_LONG)
            400 -> showToast(activity, message, Toast.LENGTH_LONG)
            401 -> {
                showToast(activity, "Session Expired", Toast.LENGTH_SHORT)
            }
            else -> showToast(activity, "Something went wrong", Toast.LENGTH_LONG)
        }
    }
}