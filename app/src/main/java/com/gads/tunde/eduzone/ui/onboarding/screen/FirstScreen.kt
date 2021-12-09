package com.gads.tunde.eduzone.ui.onboarding.screen

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.gads.tunde.eduzone.R
import com.gads.tunde.eduzone.databinding.FragmentFirstScreenBinding


class FirstScreen : Fragment() {

    private lateinit var binding: FragmentFirstScreenBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstScreenBinding.inflate(inflater, container, false)

        val viewPager2 = activity?.findViewById<ViewPager2>(R.id.viewPager)


        binding.continueButton.setOnClickListener {
            viewPager2?.currentItem = 1
        }


        return binding.root
    }

}
