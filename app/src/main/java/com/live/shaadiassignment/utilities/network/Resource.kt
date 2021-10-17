package com.live.shaadiassignment.utilities.network

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

data class Resource<out T>(val status: Status, val data: T?, val message: String?) {

    private var hasBeenHandled = false

    fun isResponseHandled() : Boolean {
        if (hasBeenHandled) {
            return hasBeenHandled
        } else {
            hasBeenHandled = true
            return false
        }
    }

    companion object {

        fun <T> success(data: T?): Resource<T>? {
            return Resource(
                Status.SUCCESS,
                data,
                null
            )
        }

        fun <T> error(msg: String, data: T?): Resource<T>? {
            return Resource(
                Status.ERROR,
                data,
                msg
            )
        }

        fun <T> loading(data: T?): Resource<T>? {
            return Resource(
                Status.LOADING,
                data,
                null
            )
        }
    }
}
