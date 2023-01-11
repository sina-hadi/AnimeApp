package com.codinginflow.animeapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.animeapp.Hero
import com.codinginflow.animeapp.util.RecipesDiffUtil
import com.codinginflow.animeapp.model.ResultHero
import com.codinginflow.animeapp.databinding.ItemHeroLayoutBinding

class HeroAdapter : RecyclerView.Adapter<HeroAdapter.MyViewHolder>() {

    var heroes: List<Hero> = emptyList()

    class MyViewHolder (private val binding: ItemHeroLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(heroes: Hero) {
            binding.heroes = heroes
            binding.executePendingBindings() // Live
        }

        companion object {
            fun from(parent: ViewGroup): MyViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHeroLayoutBinding.inflate(layoutInflater, parent, false)
                return MyViewHolder(binding)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val resultHero = heroes[position]
        holder.bind(resultHero)
    }

    override fun getItemCount(): Int {
        return heroes.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(newData: ResultHero) {
        val recipesDiffUtil = RecipesDiffUtil(heroes, newData.heroes)
        val diffUtilResult = DiffUtil.calculateDiff(recipesDiffUtil)
        heroes = newData.heroes
        diffUtilResult.dispatchUpdatesTo(this) // Should Learn Async of it (Background Thread)
    }
}