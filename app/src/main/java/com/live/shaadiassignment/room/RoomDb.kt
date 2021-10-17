package com.live.shaadiassignment.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */
@Database(entities = [ProfileEntity::class], version = 1)
abstract class RoomDb : RoomDatabase() {
    abstract fun roomDao(): RoomDao
}