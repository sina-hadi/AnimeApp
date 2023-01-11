package com.codinginflow.animeapp.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.codinginflow.animeapp.data.DataStoreRepository
import com.codinginflow.animeapp.ui.MainActivity
import com.codinginflow.animeapp.R
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

@OptIn(DelicateCoroutinesApi::class)
class ViewPagerAdapter(
    private var title: List<String>,
    private var details: List<String>,
    private var images: List<Int>
) : RecyclerView.Adapter<ViewPagerAdapter.Pager2ViewHolder>() {

    inner class Pager2ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val itemTitle: TextView = itemView.findViewById(R.id.tvTitle)
        val itemDetails: TextView = itemView.findViewById(R.id.tvAbout)
        val itemImages: ImageView = itemView.findViewById(R.id.tvImage)
        val itemButton: Button = itemView.findViewById(R.id.button)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Pager2ViewHolder {
        return Pager2ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_first_login, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Pager2ViewHolder, position: Int) {
        holder.itemTitle.text = title[position]
        holder.itemDetails.text = details[position]
        holder.itemImages.setBackgroundResource(images[position])
        if (position == title.size - 1) {
            holder.itemButton.visibility = View.VISIBLE
        } else {
            holder.itemButton.visibility = View.INVISIBLE
        }
        holder.itemButton.setOnClickListener { v ->
            GlobalScope.launch {
                saveAndPass(v)
            }
        }
    }

    override fun getItemCount(): Int {
        return title.size
    }

    private suspend fun saveAndPass(v: View) {
        val dataStoreRepository = DataStoreRepository(v.context)
        dataStoreRepository.save("key", true)
        val intent = Intent(v.context, MainActivity::class.java)
        v.context.startActivity(intent)
    }
}