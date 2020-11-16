package com.example.oketestapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.model.Show
import com.example.oketestapp.databinding.ShowItemBinding

class ShowsAdapter: ListAdapter<Show, ShowsAdapter.ViewHolder>(ShowItemDiffCallback()){
    class ShowItemDiffCallback: DiffUtil.ItemCallback<Show>() {
        override fun areItemsTheSame(oldItem: Show, newItem: Show): Boolean =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: Show, newItem: Show): Boolean  =
            oldItem == newItem

    }

    class ViewHolder(private val binding: ShowItemBinding): RecyclerView.ViewHolder(binding.root){

        fun bind(show: Show){
            binding.show = show
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ShowItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}