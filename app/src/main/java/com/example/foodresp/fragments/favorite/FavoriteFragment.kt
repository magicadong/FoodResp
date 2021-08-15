package com.example.foodresp.fragments.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodresp.R
import com.example.foodresp.data.model.Result
import com.example.foodresp.databinding.FragmentFavoriteBinding
import com.example.foodresp.viewmodel.FavoriteViewModel

class FavoriteFragment : Fragment() {
    private lateinit var binding:FragmentFavoriteBinding
    private val favoriteAdapter = FavoriteAdapter()
    private val favoriteViewModel:FavoriteViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFavoriteBinding.inflate(inflater)
        binding.recyclerView.layoutManager = LinearLayoutManager(
            context,RecyclerView.VERTICAL,false)
        binding.recyclerView.adapter = favoriteAdapter

        favoriteViewModel.readFavorites()
        favoriteViewModel.favoriteRecipes.observe(viewLifecycleOwner){
            val resultList = mutableListOf<Result>()
            it.forEach { entity ->
                resultList.add(entity.result)
            }
            favoriteAdapter.setData(resultList)
        }
        return binding.root
    }

}