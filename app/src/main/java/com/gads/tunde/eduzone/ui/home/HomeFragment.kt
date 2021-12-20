package com.gads.tunde.eduzone.ui.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gads.tunde.eduzone.R
import com.gads.tunde.eduzone.adapter.CourseHorizontalAdapter
import com.gads.tunde.eduzone.databinding.FragmentHomeBinding
import com.google.android.material.chip.Chip
import com.gads.tunde.eduzone.ui.home.HomeViewModel.NetworkResponseStatus


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

        setupFeaturedCourses()
        initializeCategories()
        setupTopCourses()
        setupNewCourses()



    }

    private fun initializeCategories() {
        val chipGroupOne = binding.coursesCategoriesChipGroupOne
        val chipGroupTwo = binding.coursesCategoriesChipGroupTwo

        val inflaterOne = LayoutInflater.from(chipGroupOne.context)
        val inflaterTwo = LayoutInflater.from(chipGroupTwo.context)

        val categoryListOne = viewModel.categoryListOne
        val categoryListTwo = viewModel.categoryListTwo

        val childrenOne = categoryListOne.map { category ->
            val chip = inflaterOne.inflate(R.layout.category, chipGroupOne, false) as Chip
            chip.text = category
            chip.tag = category
            chip
        }

        val childrenTwo = categoryListTwo.map { category ->
            val chip = inflaterTwo.inflate(R.layout.category, chipGroupTwo, false) as Chip
            chip.text = category
            chip.tag = category
            chip
        }

        for (chip in childrenOne) {
            chipGroupOne.addView(chip)
            chip.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCategoriesFragment(chip.text.toString()))
            }
        }

        for (chip in childrenTwo) {
            chipGroupTwo.addView(chip)
            chip.setOnClickListener {
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToCategoriesFragment(chip.text.toString()))
            }
        }

    }

    private fun setupFeaturedCourses() {
        val featuredCoursesAdapter = CourseHorizontalAdapter()
        binding.featuredCoursesRecyclerView.adapter = featuredCoursesAdapter
        binding.featuredCoursesRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        viewModel.featuredCourses.observe(viewLifecycleOwner, {
            featuredCoursesAdapter.submitList(it)
        })

        featuredCoursesAdapter.setOnItemClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
        }

        viewModel.featuredCourseStatus.observe(viewLifecycleOwner, { status ->
            when(status) {

                NetworkResponseStatus.LOADING ->{
                    binding.featuredCoursesRecyclerView.visibility = View.INVISIBLE
                    binding.featuredCoursesProgressBar.visibility = View.VISIBLE
                }

                NetworkResponseStatus.DONE -> {
                    binding.featuredCoursesRecyclerView.visibility = View.VISIBLE
                    binding.featuredCoursesProgressBar.visibility = View.INVISIBLE
                }
                else -> {
                    binding.featuredCoursesRecyclerView.visibility = View.INVISIBLE
                    binding.featuredCoursesProgressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupTopCourses() {
        val topCoursesAdapter = CourseHorizontalAdapter()
        binding.topCoursesRecyclerView.adapter = topCoursesAdapter
        binding.topCoursesRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        viewModel.topCourses.observe(viewLifecycleOwner, {
            topCoursesAdapter.submitList(it)
        })

        topCoursesAdapter.setOnItemClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
        }

        viewModel.topCourseStatus.observe(viewLifecycleOwner, { status ->
            when(status) {

                NetworkResponseStatus.LOADING ->{
                    binding.topCoursesRecyclerView.visibility = View.INVISIBLE
                    binding.topCoursesProgressBar.visibility = View.VISIBLE
                }

                NetworkResponseStatus.DONE -> {
                    binding.topCoursesRecyclerView.visibility = View.VISIBLE
                    binding.topCoursesProgressBar.visibility = View.INVISIBLE
                }
                else -> {
                    binding.topCoursesRecyclerView.visibility = View.INVISIBLE
                    binding.topCoursesProgressBar.visibility = View.VISIBLE
                }
            }
        })
    }

    private fun setupNewCourses() {
        val newCoursesAdapter = CourseHorizontalAdapter()
        binding.newCoursesRecyclerView.adapter = newCoursesAdapter
        binding.newCoursesRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        viewModel.newCourses.observe(viewLifecycleOwner, {
            newCoursesAdapter.submitList(it)
        })

        newCoursesAdapter.setOnItemClickListener {
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(it))
        }

        viewModel.newCourseStatus.observe(viewLifecycleOwner, { status ->
            when(status) {

                NetworkResponseStatus.LOADING ->{
                    binding.newCoursesRecyclerView.visibility = View.INVISIBLE
                    binding.newCoursesProgressBar.visibility = View.VISIBLE
                }

                NetworkResponseStatus.DONE -> {
                    binding.newCoursesRecyclerView.visibility = View.VISIBLE
                    binding.newCoursesProgressBar.visibility = View.INVISIBLE
                }
                else -> {
                    binding.newCoursesRecyclerView.visibility = View.INVISIBLE
                    binding.newCoursesProgressBar.visibility = View.VISIBLE
                }
            }
        })
    }


}