package com.gads.tunde.eduzone.ui.onboarding.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.gads.tunde.eduzone.R
import com.gads.tunde.eduzone.databinding.FragmentSecondScreenBinding

class SecondScreen : Fragment() {
   private lateinit var binding: FragmentSecondScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondScreenBinding.inflate(inflater, container, false)

        val viewPager2 = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.continueButton.setOnClickListener {
            viewPager2?.currentItem = 2
        }

        return binding.root
    }


}