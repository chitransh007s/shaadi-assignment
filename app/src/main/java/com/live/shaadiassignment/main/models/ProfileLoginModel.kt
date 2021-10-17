package com.live.shaadiassignment.main.models

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

data class ProfileLoginModel(var uuid: String, var username: String, var password: String,
                             var salt: String, var md5: String, var sha1: String, var sha256: String)
