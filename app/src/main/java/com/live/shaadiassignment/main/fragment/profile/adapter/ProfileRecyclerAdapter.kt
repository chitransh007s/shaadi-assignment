package com.live.shaadiassignment.main.fragment.profile.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.live.shaadiassignment.R
import com.live.shaadiassignment.databinding.ItemProfileBinding
import com.live.shaadiassignment.room.ProfileEntity
import com.live.shaadiassignment.utilities.glide.GlideImageLoader

/**
 * Created by Chitransh Shrivastava on 15-10-2021 for ShaadiAssignment.
 * All rights reserved.
 */

class ProfileRecyclerAdapter(private val context: Context,
                             private val profileList: List<ProfileEntity>,
                             private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ProfileRecyclerAdapter.ProfileViewHolder>() {

    private var glideImageLoader: GlideImageLoader = GlideImageLoader(context)

    interface OnItemClickListener {
        fun onAccept(position: Int)
        fun onDecline(position: Int)
    }

    inner class ProfileViewHolder(private val profileBinding: ItemProfileBinding) : RecyclerView.ViewHolder(profileBinding.root) {

        fun bind(profileEntity: ProfileEntity, position: Int) {
            glideImageLoader.loadCircleImage(profileEntity.largeImage, profileEntity.thumbnail,
                profileBinding.itemProfileImage)

            val name = "${profileEntity.first} ${profileEntity.last}"
            profileBinding.itemProfileTitle.text = name

            val content = "${profileEntity.age}, ${profileEntity.city}, ${profileEntity.state}, ${profileEntity.country}"
            profileBinding.itemProfileContent.text = content

            when(profileEntity.isAccepted) {
                1 -> {
                    profileBinding.itemProfileAccept.text = context.getString(R.string.profile_accepted)

                    profileBinding.itemProfileAccept.visibility = View.VISIBLE
                    profileBinding.itemProfileDecline.visibility = View.GONE
                }
                2 -> {
                    profileBinding.itemProfileDecline.text = context.getString(R.string.profile_declined)

                    profileBinding.itemProfileAccept.visibility = View.GONE
                    profileBinding.itemProfileDecline.visibility = View.VISIBLE
                }
                else -> {
                    profileBinding.itemProfileAccept.text = context.getString(R.string.item_profile_accept)
                    profileBinding.itemProfileDecline.text = context.getString(R.string.item_profile_decline)

                    profileBinding.itemProfileAccept.visibility = View.VISIBLE
                    profileBinding.itemProfileDecline.visibility = View.VISIBLE
                }
            }

            profileBinding.itemProfileAccept.setOnClickListener {
                if (profileEntity.isAccepted == 0) {
                    listener.onAccept(position)
                }
            }

            profileBinding.itemProfileDecline.setOnClickListener {
                if (profileEntity.isAccepted == 0) {
                    listener.onDecline(position)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        return ProfileViewHolder(ItemProfileBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        holder.bind(profileList[position], position)
    }

    override fun getItemCount(): Int {
        return profileList.size
    }
}