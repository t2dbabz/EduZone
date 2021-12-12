package com.gads.tunde.eduzone.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
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
        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewModel : HomeViewModel by lazy {
            ViewModelProvider(this)[HomeViewModel::class.java]
        }

        val adapter = CoursesAdapter()

        viewModel.courseList.observe(viewLifecycleOwner, { courseList ->
            println(" Course list ${courseList.size}")
            adapter.submitList(courseList)
        })

        binding.recyclerView.adapter = adapter

        adapter.setOnItemClickListener {  selectedCourse ->
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(selectedCourse))
        }

    }


}