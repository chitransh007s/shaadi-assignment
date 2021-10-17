package com.live.shaadiassignment.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.live.shaadiassignment.main.repository.MainRepository
import com.live.shaadiassignment.utilities.livedatawrapper.Event
import com.live.shaadiassignment.main.models.ProfileModel
import com.live.shaadiassignment.main.models.ProfileResponseModel
import com.live.shaadiassignment.room.ProfileEntity
import com.live.shaadiassignment.room.ProfileMapper
import com.live.shaadiassignment.room.RoomRepo
import com.live.shaadiassignment.utilities.network.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

@HiltViewModel
class MainViewModel @Inject constructor(private val mainRepository: MainRepository,
                                        private val roomRepo: RoomRepo,
                                        private val profileMapper: ProfileMapper) : ViewModel() {

    private val _backPressed: MutableLiveData<Event<Boolean>> = MutableLiveData()
    val backPressed: LiveData<Event<Boolean>> get() = _backPressed

    private val _profileLiveData: MutableLiveData<Resource<ProfileResponseModel>> = MutableLiveData()
    val profileLiveData: LiveData<Resource<ProfileResponseModel>> get() = _profileLiveData



    fun backPressed() {
        _backPressed.postValue(Event(true))
    }

    fun requestProfiles(results: Int) {
        viewModelScope.launch {
            _profileLiveData.postValue(Resource.loading(null))
            mainRepository.getProfiles(results).let {
                if (it!!.isSuccessful){
                    it.body()?.let { profileResponseModel ->
                        roomRepo.insertAllProfiles(profileMapper.mapRestProfile(profileResponseModel.profileList))
                        _profileLiveData.postValue(Resource.success(null))
                    }
                } else {
                    _profileLiveData.postValue(Resource.error("Something Went Wrong", null))
                }
            }
        }
    }

    fun getAllProfile(): LiveData<List<ProfileEntity>> = roomRepo.getAllProfile()

    fun updateProfile(profileEntity: ProfileEntity): LiveData<Long> = roomRepo.insertProfile(profileEntity)

}