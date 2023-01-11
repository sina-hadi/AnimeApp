package com.codinginflow.animeapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.animeapp.databinding.PropertiesLayoutBinding

class DetailsAdapter2 : RecyclerView.Adapter<DetailsAdapter2.MyViewHolder>() {

    var properties: List<String> = emptyList()

    class MyViewHolder(private val binding: PropertiesLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(property: List<String>) {
            binding.property = property
            binding.executePendingBindings() // Live
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = PropertiesLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val ability = properties[position]
        val property: List<String> = listOf("${position + 1}.", ability)
        holder.bind(property)
    }

    override fun getItemCount(): Int {
        return properties.size
    }

    fun setData(data: List<String>) {
        properties = data
    }
}