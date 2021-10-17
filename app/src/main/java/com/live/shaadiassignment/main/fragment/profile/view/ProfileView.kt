package com.live.shaadiassignment.main.fragment.profile.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.live.shaadiassignment.R
import com.live.shaadiassignment.databinding.FragmentProfileBinding
import com.live.shaadiassignment.main.fragment.profile.adapter.ProfileRecyclerAdapter
import com.live.shaadiassignment.main.viewmodel.MainViewModel
import com.live.shaadiassignment.room.ProfileEntity
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.WithFragmentBindings
import androidx.recyclerview.widget.PagerSnapHelper

import androidx.recyclerview.widget.SnapHelper




/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

@AndroidEntryPoint
@WithFragmentBindings
class ProfileView : Fragment() {


    //Fragment Variables
    private lateinit var binding: FragmentProfileBinding
    private var profileList: ArrayList<ProfileEntity> = ArrayList()
    private lateinit var profileRecyclerAdapter: ProfileRecyclerAdapter
    private val mainViewModel: MainViewModel by activityViewModels()



    //Override Methods

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        setListeners()
        initRecycler()
        fetchMatches()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProfileView()
    }



    //Methods

    private fun initViews() {
        binding.profileToolbar.toolbarTitle.text = getString(R.string.profile_title)
    }

    private fun setListeners() {
        binding.profileToolbar.toolbarBack.setOnClickListener { mainViewModel.backPressed()}
    }

    private fun initRecycler(){
        profileRecyclerAdapter = ProfileRecyclerAdapter(requireContext(), profileList, object : ProfileRecyclerAdapter.OnItemClickListener {
            override fun onAccept(position: Int) {
                profileList[position].isAccepted = 1
                mainViewModel.updateProfile(profileList[position])
            }

            override fun onDecline(position: Int) {
                profileList[position].isAccepted = 2
                mainViewModel.updateProfile(profileList[position])
            }
        })
        binding.profileRecycler.layoutManager = LinearLayoutManager(context,
            LinearLayoutManager.HORIZONTAL, false
        )
        binding.profileRecycler.itemAnimator = DefaultItemAnimator()
        binding.profileRecycler.adapter = profileRecyclerAdapter
        val snapHelper: SnapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.profileRecycler)
    }

    private fun fetchMatches() {
        mainViewModel.getAllProfile().observe(viewLifecycleOwner) { list ->
            profileList.clear()
            profileList.addAll(list)

            if (profileList.size > 0) {
                profileRecyclerAdapter.notifyDataSetChanged()
                binding.profileRecycler.visibility = View.VISIBLE
                binding.profileListEmpty.visibility = View.GONE
            } else {
                binding.profileRecycler.visibility = View.GONE
                binding.profileListEmpty.visibility = View.VISIBLE
            }
        }
    }

}
