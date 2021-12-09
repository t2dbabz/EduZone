package com.gads.tunde.eduzone.ui.onboarding

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.gads.tunde.eduzone.R
import com.gads.tunde.eduzone.databinding.FragmentViewPagerBinding
import com.gads.tunde.eduzone.ui.onboarding.screen.FirstScreen
import com.gads.tunde.eduzone.ui.onboarding.screen.SecondScreen
import com.gads.tunde.eduzone.ui.onboarding.screen.ThirdScreen
import com.google.android.material.tabs.TabLayoutMediator


class ViewPagerFragment : Fragment() {

    private lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewPagerBinding.inflate(inflater, container, false)

        val fragmentList = arrayListOf(FirstScreen(), SecondScreen(), ThirdScreen())

        val adapter = ViewPagerAdapter(fragmentList, requireActivity().supportFragmentManager, lifecycle)

        binding.viewPager.adapter = adapter

        TabLayoutMediator(binding.tabLayout, binding.viewPager){ _, _->

        }.attach()

        return binding.root
    }


}