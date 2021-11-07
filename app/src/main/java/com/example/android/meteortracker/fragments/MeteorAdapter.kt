package com.example.android.meteortracker.fragments

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.meteortracker.bussinessdata.Meteor
import com.example.android.meteortracker.databinding.ListItemBinding

class MeteorAdapter(val clickListener: MeteorListener) : ListAdapter<Meteor, MeteorAdapter.MeteorVH>(
    MeteorDiffCallback()
){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MeteorVH {
        return MeteorVH.from(parent)
    }

    override fun onBindViewHolder(holder: MeteorVH, position: Int) {
        val item =getItem(position)
        holder.bind(item,clickListener)
    }

    class MeteorVH(val binding: ListItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: Meteor, clickListener: MeteorListener){
            binding.meteor = item
            binding.clickListener = clickListener
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): MeteorVH {
                val layoutInflater =LayoutInflater.from(parent.context)
                val binding = ListItemBinding.inflate(layoutInflater,parent,false)
                return MeteorVH(binding)
            }
        }
    }
}

class MeteorDiffCallback: DiffUtil.ItemCallback<Meteor>(){
    override fun areItemsTheSame(oldItem: Meteor, newItem: Meteor): Boolean {
        return newItem.id == oldItem.id
    }

    override fun areContentsTheSame(oldItem: Meteor, newItem: Meteor): Boolean {
        return newItem == oldItem
    }

}

class MeteorListener(val clickListener: (meteor: Meteor)->Unit){
    fun onClick(meteor: Meteor) = clickListener(meteor)
}