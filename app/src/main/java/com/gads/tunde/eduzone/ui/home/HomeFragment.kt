package com.gads.tunde.eduzone.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.gads.tunde.eduzone.R
import com.gads.tunde.eduzone.adapter.CoursesAdapter
import com.gads.tunde.eduzone.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {


    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        val viewModel : HomeViewModel by lazy {
            ViewModelProvider(this)[HomeViewModel::class.java]
        }

        val adapter = CoursesAdapter()



        viewModel.courseList.observe(viewLifecycleOwner, { courseList ->
            println(" Course list ${courseList.size}")
            adapter.submitList(courseList)
        })

        binding.recyclerView.adapter = adapter

        return binding.root
    }


}