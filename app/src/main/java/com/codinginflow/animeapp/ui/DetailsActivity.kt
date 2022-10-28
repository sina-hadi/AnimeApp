package com.codinginflow.animeapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.codinginflow.animeapp.R
import com.codinginflow.animeapp.adapter.DetailsAdapter
import com.codinginflow.animeapp.adapter.DetailsAdapter2
import com.codinginflow.animeapp.adapter.DetailsAdapter3
import com.codinginflow.animeapp.databinding.ActivityDetailsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailsActivity : AppCompatActivity() {

    private val mAdapter by lazy { DetailsAdapter() }
    private val mAdapter2 by lazy { DetailsAdapter2() }
    private val mAdapter3 by lazy { DetailsAdapter3() }


    private val args by navArgs<DetailsActivityArgs>()

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.hero = args.heroes

        mAdapter.setData(args.heroes.family)
        mAdapter2.setData(args.heroes.abilities)
        mAdapter3.setData(args.heroes.natureTypes)

        setupRecyclerView()
        setupRecyclerView2()
        setupRecyclerView3()




        setSupportActionBar(findViewById(R.id.toolbar))
    }

    private fun setupRecyclerView() {
        binding.rvFamily.adapter = mAdapter
        binding.rvFamily.layoutManager = LinearLayoutManager(this)
    }

    private fun setupRecyclerView2() {
        binding.rvAbilities.adapter = mAdapter2
        binding.rvAbilities.layoutManager = LinearLayoutManager(this)
    }

    private fun setupRecyclerView3() {
        binding.rvNature.adapter = mAdapter3
        binding.rvNature.layoutManager = LinearLayoutManager(this)
    }
}