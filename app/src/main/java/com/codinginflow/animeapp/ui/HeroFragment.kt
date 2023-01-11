package com.codinginflow.animeapp.ui

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.core.view.MenuHost
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.codinginflow.animeapp.R
import com.codinginflow.animeapp.adapter.HeroAdapter
import com.codinginflow.animeapp.databinding.FragmentHeroBinding
import com.codinginflow.animeapp.util.NetworkResult
import com.codinginflow.animeapp.viewmodel.MainViewMode
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HeroFragment : Fragment(), SearchView.OnQueryTextListener {

    private val mAdapter by lazy { HeroAdapter() }
    private var _binding: FragmentHeroBinding? = null
    private val binding get() = _binding!!
    private lateinit var mainViewModel: MainViewMode

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainViewModel = ViewModelProvider(requireActivity())[MainViewMode::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHeroBinding.inflate(inflater, container, false)

        setupRecyclerView()

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun setupRecyclerView() {
        binding.recipesRecyclerView.adapter = mAdapter
        binding.recipesRecyclerView.layoutManager = LinearLayoutManager(requireContext())
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuHost: MenuHost = requireHost() as MenuHost
        menuHost.addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.recipes_menu, menu)

                val search = menu.findItem(R.id.menu_search)
                val searchView = search.actionView as? SearchView
                searchView?.isSubmitButtonEnabled = true
                searchView?.setOnQueryTextListener(this@HeroFragment)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                when (menuItem.itemId) {
                    R.id.page1 -> requestApiData(1)
                    R.id.page2 -> requestApiData(2)
                    R.id.page3 -> requestApiData(3)
                    R.id.page4 -> requestApiData(4)
                    R.id.page5 -> requestApiData(5)
                    R.id.offlineMode -> requestApiData(6)
                }
                return true
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        if (query != null) {
            searchApiData(query)
        }
        return true
    }

    private fun searchApiData(searchQuery: String) {
        Log.e("ABCD", searchQuery)
        mainViewModel.searchRecipes(searchQuery)
        mainViewModel.searchedRecipesResponse.observe(viewLifecycleOwner) { response ->
            if (response is NetworkResult.Success) {
                Log.e("ABCD", "2")
                response.data?.let { mAdapter.setData(it) }
            }
        }
    }


    override fun onQueryTextChange(newText: String?): Boolean {
        return true
    }

    private fun requestApiData(page: Int) {
        lifecycleScope.launch {
            mainViewModel.getRecipes(page)
            mainViewModel.recipesResponse.observe(viewLifecycleOwner) { response ->
                if (response is NetworkResult.Success) {
                    response.data?.let { mAdapter.setData(it) }
                }
            }
        }
    }

}