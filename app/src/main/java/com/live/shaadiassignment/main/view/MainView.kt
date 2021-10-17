package com.live.shaadiassignment.main.view

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.live.shaadiassignment.R
import com.live.shaadiassignment.databinding.ActivityMainBinding
import com.live.shaadiassignment.main.fragment.profile.view.ProfileView
import com.live.shaadiassignment.main.viewmodel.MainViewModel
import com.live.shaadiassignment.utilities.helpers.BaseActivity
import com.live.shaadiassignment.utilities.helpers.Constants
import com.live.shaadiassignment.utilities.network.Status
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

@AndroidEntryPoint
class MainView : BaseActivity() {

    // Activity Variables
    private var results: Int = 10
    private var doubleBackPressed = false
    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()

    // Override Methods
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setListeners()
        initViewModels()
        initDefaultFragment()
        fetchAndStoreProfiles()

    }

    override fun onBackPressed() {
        if (doubleBackPressed) {
            finish()
        }
        doubleBackPressed = true
        showToastMessage("Press twice to exit")
        Handler(Looper.getMainLooper()).postDelayed({ doubleBackPressed = false }, 2000)
    }



    // Methods
    private fun initViewModels() {
        mainViewModel.backPressed.observe(this, { event ->
            if (event.getContentIfNotHandled() != null) {
                onBackPressed()
            }
        })

        mainViewModel.profileLiveData.observe(this, {
            when(it.status) {
                Status.LOADING -> {
                    showSnackBar(binding.mainViewGroup, "Fetching Matches")
                }

                Status.SUCCESS -> {
                    showSnackBar(binding.mainViewGroup, "Matches Fetched")
                }

                Status.ERROR -> {
                    showSnackBar(binding.mainViewGroup, it.message)
                }
            }
        })

    }

    private fun initDefaultFragment() {
        openFragment(ProfileView.newInstance(), Constants.PROFILE_FRAGMENT_TAG)
    }

    private fun setListeners() {

    }

    private fun openFragment(newFragment: Fragment, tag: String) {
        addFragment(newFragment, tag)
    }

    private fun addFragment(fragment: Fragment, tag: String) {
        val transaction =
            supportFragmentManager.beginTransaction()
        setFragmentTransactionAnimation(transaction, tag)
        transaction.add(R.id.main_container, fragment, tag)
        transaction.addToBackStack(tag)
        transaction.commit()
    }

    private fun setFragmentTransactionAnimation(transaction: FragmentTransaction, tag: String) {
        transaction.setCustomAnimations(
            R.anim.enter_from_right,
            R.anim.exit_to_left,
            R.anim.enter_from_left,
            R.anim.exit_to_right
        )
    }

    private fun fetchAndStoreProfiles() {
        mainViewModel.requestProfiles(results)
    }
}
