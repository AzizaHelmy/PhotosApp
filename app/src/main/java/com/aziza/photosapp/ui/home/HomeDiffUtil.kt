package com.aziza.photosapp.ui.home

import androidx.recyclerview.widget.DiffUtil

class HomeDiffUtil<Item:DifferentiableItem>: DiffUtil.ItemCallback<Item>() {

    override fun areItemsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.getUniqueIdentifier() == newItem.getUniqueIdentifier()
    }

    override fun areContentsTheSame(oldItem: Item, newItem: Item): Boolean {
        return oldItem.getContent() == newItem.getContent()
    }

    companion object {
        fun <Item : DifferentiableItem> getInstance(): HomeDiffUtil<Item> {
            return HomeDiffUtil()
        }
    }
}