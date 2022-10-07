package com.aziza.photosapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aziza.photosapp.databinding.ItemRvPhotosBinding
import com.squareup.picasso.NetworkPolicy
import com.squareup.picasso.Picasso

class HomeAdapter(val onClickListener: IHomeOnClickListener) :
    ListAdapter<Photo, HomeAdapter.HomeViewHolder>(HomeDiffUtil.getInstance()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(
            ItemRvPhotosBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class HomeViewHolder(private val binding: ItemRvPhotosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            binding.apply {
                Picasso.get().load(photo.url).networkPolicy(NetworkPolicy.OFFLINE).into(ivPhoto)
                tvTitle.text = photo.title
                ivPhoto.setOnClickListener {
                    onClickListener.onPhotoClicked(photo)
                }
                layoutHome.setOnClickListener {
                    onClickListener.onItemClicked()
                }
            }
        }
    }
}

