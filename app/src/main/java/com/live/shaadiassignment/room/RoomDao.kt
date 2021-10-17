package com.live.shaadiassignment.room

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */
@Dao
abstract class RoomDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertProfile(profileEntity: ProfileEntity): Long

    @Transaction
    open fun insertAllProfile(profileList: List<ProfileEntity>) {
        for (profileEntity in profileList) {
            insertProfile(profileEntity)
        }
    }

    @Query("SELECT * FROM ProfileEntity WHERE id =:id")
    abstract fun getProfile(id: String): LiveData<ProfileEntity>

    @Query("SELECT * FROM ProfileEntity order by first")
    abstract fun getAllProfiles(): LiveData<List<ProfileEntity>>

    @Delete
    abstract fun deleteProfile(profileEntity: ProfileEntity): Int

    @Query("DELETE FROM ProfileEntity")
    abstract fun deleteAllProfile(): Int
}