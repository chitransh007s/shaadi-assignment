package com.live.shaadiassignment.room

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Created by Chitransh Shrivastava on 17-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */
@Entity
data class ProfileEntity(@PrimaryKey(autoGenerate = false) val id: String,
                         val title: String,
                         val first: String,
                         val last: String,
                         var age: Int,
                         val phone: String,
                         val largeImage: String,
                         val thumbnail: String,
                         val city: String,
                         val state: String,
                         val country: String,
                         var isAccepted: Int
)