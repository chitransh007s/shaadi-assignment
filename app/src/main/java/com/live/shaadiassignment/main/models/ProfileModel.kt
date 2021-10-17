package com.live.shaadiassignment.main.models


/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

data class ProfileModel(var gender: String, var name: ProfileNameModel, var location: ProfileLocationModel,
                        var email: String, var login: ProfileLoginModel, var dob: ProfileDobModel,
                        var phone: String, var cell: String, var picture: ProfilePictureModel, var nat: String)