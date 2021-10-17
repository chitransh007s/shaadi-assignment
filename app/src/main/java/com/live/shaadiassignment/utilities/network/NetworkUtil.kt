package com.live.shaadiassignment.utilities.network

import android.content.Context
import android.net.ConnectivityManager


object NetworkUtil {
    /**
     * Helper to check Network State if the device
     */
    @JvmStatic
    fun isNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            (context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager)
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}