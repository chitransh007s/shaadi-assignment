package com.live.shaadiassignment.utilities.network

import okhttp3.ResponseBody
import retrofit2.HttpException

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

open class ResponseHandler {
    fun <T : Any> handleSuccess(data: T): Resource<T>? {
        return Resource.success(data)
    }

    fun <T : Any> handleException(e: Exception): Resource<T>? {

        e.printStackTrace()

        return when (e) {
            is HttpException -> {

                val errorMessage = if (e.code() != 400 && e.code() != 403) {
                    getErrorMessage(e.code(), e)
                } else {
                    val body = e.response()?.errorBody()
                    getErrorJSON(body)
                }

                Resource.error(errorMessage, null)
            }

            else -> Resource.error(getErrorMessage(Int.MAX_VALUE, e), null)
        }
    }

    private fun getErrorJSON(body: ResponseBody?): String {
        return try {
            body!!.string()
        } catch (e: Exception) {
            "Something wrong happened"
        }
    }

    private fun getErrorMessage(code: Int, e: Exception): String {
        return when (code) {
            401 -> "Unauthorised"
            404 -> "Not found"
            400 -> e.message.toString()
            429 -> "Try after sometime"
            500 -> "Internal Server Error"
            else -> "Something went wrong"
        }
    }
}
