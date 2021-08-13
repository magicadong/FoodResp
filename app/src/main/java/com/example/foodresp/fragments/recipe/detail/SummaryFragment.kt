package com.example.foodresp.fragments.recipe.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.foodresp.R
import com.example.foodresp.databinding.FragmentSummaryBinding

class SummaryFragment(private val summary:String) : Fragment() {
    private lateinit var binding:FragmentSummaryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSummaryBinding.inflate(inflater)
        binding.summaryTextView.text = summary
        return binding.root
    }

}