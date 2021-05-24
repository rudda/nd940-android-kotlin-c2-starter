package com.udacity.asteroidradar.main.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.udacity.asteroidradar.data.AsteroidDatabase
import com.udacity.asteroidradar.databinding.AsteroidItemBinding
import kotlinx.android.synthetic.main.asteroid_item.view.*

class AsteroidAdapter (private val listener : OnClick?) : ListAdapter<AsteroidDatabase, AsteroidAdapter.ViewHolder>(AsteroidDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if(listener != null) {
            holder.itemView.setOnClickListener {
                listener.onAsteroidClickListener(getItem(position))
            }
        }
        holder.bind(item)
    }


    class ViewHolder private  constructor(private val binding: AsteroidItemBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: AsteroidDatabase) {
            binding.myAsteroid = item
        }

        companion object {
            fun from(parent: ViewGroup): ViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = AsteroidItemBinding.inflate(layoutInflater, parent, false)
                return ViewHolder(binding)
            }
        }
    }
    class AsteroidDiffCallback : DiffUtil.ItemCallback<AsteroidDatabase>() {
        override fun areItemsTheSame(
            oldItem: AsteroidDatabase,
            newItem: AsteroidDatabase
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: AsteroidDatabase,
            newItem: AsteroidDatabase
        ): Boolean {
            return oldItem == newItem //its works because are data classes
        }

    }

    interface OnClick {
        fun onAsteroidClickListener(asteroid : AsteroidDatabase)
    }

}