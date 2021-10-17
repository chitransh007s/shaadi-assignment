package com.live.shaadiassignment.room

import android.os.Handler
import android.os.Looper
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import javax.inject.Inject

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */
class RoomRepo @Inject constructor (private val roomDao: RoomDao) {

    //Profile Queries
    fun insertProfile(profileEntity: ProfileEntity): LiveData<Long> {
        val responseLiveData = MutableLiveData<Long>()
        val thread = Thread {
            val id = roomDao.insertProfile(profileEntity)
            Handler(Looper.getMainLooper()).post { responseLiveData.postValue(id) }
        }
        thread.start()
        return responseLiveData
    }

    fun insertAllProfiles(profileEntityList: List<ProfileEntity>): LiveData<Long> {
        val responseLiveData = MutableLiveData<Long>()
        val thread = Thread {
            roomDao.insertAllProfile(profileEntityList)
            Handler(Looper.getMainLooper()).post { responseLiveData.postValue(0L) }
        }
        thread.start()
        return responseLiveData
    }

    fun getProfile(email: String): LiveData<ProfileEntity> {
        return roomDao.getProfile(email)
    }

    fun getAllProfile(): LiveData<List<ProfileEntity>> {
        return roomDao.getAllProfiles()
    }

    fun deleteProfile(email: String): LiveData<Long> {
        val responseLiveData = MutableLiveData<Long>()
        val profileEntity = getProfile(email)
        val thread = Thread {
            val rows = roomDao.deleteProfile(profileEntity.value!!).toLong()
            Handler(Looper.getMainLooper()).post { responseLiveData.postValue(rows) }
        }
        thread.start()
        return responseLiveData
    }

    fun deleteAllProfile(): LiveData<Long> {
        val responseLiveData = MutableLiveData<Long>()
        val thread = Thread {
            val rows = roomDao.deleteAllProfile().toLong()
            Handler(Looper.getMainLooper()).post { responseLiveData.postValue(rows) }
        }
        thread.start()
        return responseLiveData
    }
}