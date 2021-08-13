package com.example.foodresp.fragments.recipe.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.example.foodresp.databinding.FragmentDetailBinding
import com.example.foodresp.fragments.recipe.adapter.ViewPagerAdapter


class DetailFragment : Fragment() {
    private lateinit var binding:FragmentDetailBinding
    private val recipeArgs:DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater)
        binding.detailBtn.isSelected = true
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recipe = recipeArgs.recipe
        binding.executePendingBindings()
        initEvent()
        initViewPager()
    }
    private fun initViewPager(){
        val fragments = listOf(
            SummaryFragment(recipeArgs.recipe.summary),
            IngredientFragment(recipeArgs.recipe.extendedIngredients))
        binding.viewPager.adapter = ViewPagerAdapter(
            fragments,requireActivity().supportFragmentManager,lifecycle)
    }
    private fun initEvent(){
        binding.backBtn.setOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.detailBtn.setOnClickListener {
            selectDetail()
            binding.viewPager.currentItem = 0
        }
        binding.ingredientsBtn.setOnClickListener {
            selectIngredient()
            binding.viewPager.currentItem = 1
        }

        binding.viewPager.registerOnPageChangeCallback(object:ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                if (position == 0){
                    selectDetail()
                }else{
                    selectIngredient()
                }
            }
        })
    }
    private fun selectIngredient(){
        if (!binding.ingredientsBtn.isSelected) {
            binding.ingredientsBtn.isSelected = true
            binding.detailBtn.isSelected = false
            val space = binding.ingredientsBtn.x-binding.detailBtn.x
            indicatorAnim(space)
        }
    }
    private fun selectDetail(){
        if (!binding.detailBtn.isSelected) {
            binding.detailBtn.isSelected = true
            binding.ingredientsBtn.isSelected = false
            val space = binding.detailBtn.x - binding.ingredientsBtn.x
            indicatorAnim(0f)
        }
    }
    private fun indicatorAnim(value:Float){
        binding.indicatorView.animate()
            .translationX(value)
            .setDuration(300)
            .start()
    }
}