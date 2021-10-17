package com.live.shaadiassignment.room

import com.live.shaadiassignment.main.models.ProfileModel

/**
 * Created by Chitransh Shrivastava on 17-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */
interface ProfileMapper {

    fun mapRestProfile(profileList: List<ProfileModel>): List<ProfileEntity>

}

class ProfileMapperImpl : ProfileMapper {

    override fun mapRestProfile(profileList: List<ProfileModel>): List<ProfileEntity> {
        val profileListMapped = ArrayList<ProfileEntity>()
        for (profileModel in profileList) {
            profileListMapped.add(ProfileEntity(
                profileModel.login.uuid,
                profileModel.name.title,
                profileModel.name.first,
                profileModel.name.last,
                profileModel.dob.age,
                profileModel.phone,
                profileModel.picture.large,
                profileModel.picture.thumbnail,
                profileModel.location.city,
                profileModel.location.state,
                profileModel.location.country,
                0)
            )
        }
        return profileListMapped
    }

}