package com.live.shaadiassignment.di

import android.content.Context
import androidx.room.Room
import com.live.shaadiassignment.room.ProfileMapper
import com.live.shaadiassignment.room.ProfileMapperImpl
import com.live.shaadiassignment.room.RoomDao
import com.live.shaadiassignment.room.RoomDb
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

@Module
@InstallIn(SingletonComponent::class)
object DbModule {
    @Provides
    fun provideRoomDao(roomDb: RoomDb): RoomDao {
        return roomDb.roomDao()
    }

    @Provides
    @Singleton
    fun provideRoomDB(@ApplicationContext appContext: Context): RoomDb {
        return Room.databaseBuilder(
            appContext,
            RoomDb::class.java,
            "Shaadi_db"
        ).build()
    }

    @Provides
    fun provideRoomMapper(): ProfileMapper {
        return ProfileMapperImpl()
    }
}