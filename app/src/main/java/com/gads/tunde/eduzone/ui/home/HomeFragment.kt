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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gads.tunde.eduzone.R
import com.gads.tunde.eduzone.adapter.CourseHorizontalAdapter
import com.gads.tunde.eduzone.adapter.CoursesAdapter
import com.gads.tunde.eduzone.databinding.FragmentHomeBinding
import com.google.android.material.chip.Chip


class HomeFragment : Fragment() {

    private val viewModel : HomeViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, HomeViewModelFactory(activity.application))[HomeViewModel::class.java]
    }


    private lateinit var binding: FragmentHomeBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
//        (activity as AppCompatActivity).supportActionBar?.show()
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeCategories()

        binding.recyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        val adapter = CourseHorizontalAdapter()

        binding.recyclerView.adapter = adapter

        binding.topCoursesRecyclerView.layoutManager =  LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)
        binding.topCoursesRecyclerView.adapter = adapter




        viewModel.coursesList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

//        adapter.setOnItemClickListener {  selectedCourse ->
//            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(selectedCourse))
//        }

    }

    private fun initializeCategories() {
        val chipGroupOne = binding.coursesCategoriesChipGroupOne
        val chipGroupTwo = binding.coursesCategoriesChipGroupTwo

        val inflatorOne = LayoutInflater.from(chipGroupOne.context)
        val inflatorTwo = LayoutInflater.from(chipGroupTwo.context)

        val categoryListOne = listOf("Business", "Development", "IT & Software", "Lifestyle", "Design",
            "Teaching & Academics", "Photography & Video")
        val categoryListTwo = listOf("Personal Development", "Office Productivity", "Finance & Accounting", "Marketing", "Music")

        val childrenOne = categoryListOne.map { category ->
            val chip = inflatorOne.inflate(R.layout.category, chipGroupOne, false) as Chip
            chip.text = category
            chip.tag = category
            chip.setOnCheckedChangeListener { button, isChecked ->

            }
            chip
        }
        val childrenTwo = categoryListTwo.map { category ->
            val chip = inflatorTwo.inflate(R.layout.category, chipGroupTwo, false) as Chip
            chip.text = category
            chip.tag = category
            chip.setOnCheckedChangeListener { button, isChecked ->

            }
            chip
        }

        for (chip in childrenOne) {
            chipGroupOne.addView(chip)
        }

        for (chip in childrenTwo) {
            chipGroupTwo.addView(chip)
        }
    }


}