package com.codinginflow.animeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.codinginflow.animeapp.R
import com.codinginflow.animeapp.adapter.ViewPagerAdapter
import com.codinginflow.animeapp.databinding.ActivityFirstTimeBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FirstTimeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFirstTimeBinding

    private var titleList = mutableListOf<String>()
    private var descList = mutableListOf<String>()
    private var imagesList = mutableListOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFirstTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postToList()

        binding.viewPager2.adapter = ViewPagerAdapter(titleList, descList, imagesList)
        binding.viewPager2.orientation = ViewPager2.ORIENTATION_HORIZONTAL
        binding.indicator.setViewPager(binding.viewPager2)

    }

    private fun addToList(title: String, description: String, image: Int) {
        titleList.add(title)
        descList.add(description)
        imagesList.add(image)
    }

    private fun postToList() {
        addToList(
            "Greetings",
            "Are you a Boruto fan? Because if you are then we have a great news for you!",
            R.drawable.greetings
        )
        addToList(
            "Explore",
            "Find your favorite heroes and learn some of the things that you didn't know about.",
            R.drawable.explore
        )
        addToList(
            "Power",
            "Check out your heroes power and see how much are they strong comparing to others.",
            R.drawable.power
        )
    }

    @Deprecated("Deprecated in Java", ReplaceWith("finish()"))
    override fun onBackPressed() {
        finishAffinity()
    }
}