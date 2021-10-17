package com.live.shaadiassignment.main.models

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

data class ProfileLocationModel(var street: ProfileLocationStreetModel, var city: String, var state: String,
                                var country: String, var pincode: Int, var coordinates: ProfileLocationCoordinatesModel,
                                var timezone: ProfileLocationTimezoneModel)