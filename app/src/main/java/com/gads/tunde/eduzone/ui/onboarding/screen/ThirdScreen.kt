package com.gads.tunde.eduzone.ui.onboarding.screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.gads.tunde.eduzone.R
import com.gads.tunde.eduzone.databinding.FragmentThirdScreenBinding

class ThirdScreen : Fragment() {

    private lateinit var binding: FragmentThirdScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentThirdScreenBinding.inflate(inflater, container, false)

        binding.startButton.setOnClickListener {
            onBoarding()
            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment)
        }

        return binding.root
    }

    private fun onBoarding(){
        val sharedPref = requireActivity().getSharedPreferences("onBoarding", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()
        editor.putBoolean("Finished", true)
        editor.apply()
    }

}