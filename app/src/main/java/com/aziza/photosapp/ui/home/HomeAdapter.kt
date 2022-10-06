package com.aziza.photosapp.ui.home

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.aziza.photosapp.databinding.ItemRvPhotosBinding
import com.bumptech.glide.Glide

//class HomeAdapter : ListAdapter<Photo, HomeAdapter.HomeViewHolder>(HomeDiffUtil) {

class HomeAdapter(val onClickListener: IHHomeOnClickListener) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    private var photos: List<Photo> = ArrayList()

    fun setData(newList: List<Photo>) {
//        val homeDiffUtil = HomeDiffUtil(photos, newList)
//        val homeDiffutilResult = DiffUtil.calculateDiff(homeDiffUtil)
        photos = newList
        notifyDataSetChanged()
        //  homeDiffutilResult.dispatchUpdatesTo(this)
    }

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
        holder.bind(photos[position])

    }

    override fun getItemCount(): Int = photos.size

    inner class HomeViewHolder(private val binding: ItemRvPhotosBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(photo: Photo) {
            binding.apply {
                Glide.with(ivPhoto.context)
                    //.load(photo.thumbnailUrl)
                    .load("https://rickandmortyapi.com/api/character/avatar/473.jpeg")
                    // .placeholder(R.drawable.img)
                    .into(ivPhoto)
                Log.e("TAG", "bind:${photo.url} ")
                tvTitle.text = photo.title
                ivPhoto.setOnClickListener {
                    onClickListener.onPhotoClicked(photo)
                }
                layoutHome.setOnClickListener {

                }
            }


        }

    }
}