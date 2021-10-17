package com.live.shaadiassignment.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

@HiltAndroidApp
class BaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {

        private var instance: BaseApplication? = null

        @Synchronized
        fun getInstance(): BaseApplication? {
            return instance
        }
    }

}