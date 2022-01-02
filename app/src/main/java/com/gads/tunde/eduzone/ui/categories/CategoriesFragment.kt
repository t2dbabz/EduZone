package com.gads.tunde.eduzone.ui.categories

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
import com.gads.tunde.eduzone.ui.home.HomeViewModel.NetworkResponseStatus
import com.gads.tunde.eduzone.adapter.CourseHorizontalAdapter
import com.gads.tunde.eduzone.databinding.FragmentCategoriesBinding

class CategoriesFragment : Fragment() {

    private lateinit var binding: FragmentCategoriesBinding

    private val viewModel: CategoriesViewModel by lazy {
        val activity = requireNotNull(this.activity ) {}
        ViewModelProvider(this, CategoriesViewModelFactory(activity.application))[CategoriesViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentCategoriesBinding.inflate(inflater, container, false)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        val category  = CategoriesFragmentArgs.fromBundle(requireArguments()).selectedCategory

        viewModel.getStartedCourses(category)
        setupGetStartedCourses()
        viewModel.getFeaturedCourses(category)
        setupFeaturedCourses()
        viewModel.getPopularCourses(category)
        setupPopularCourses()

        binding.apply {
            categoriesLabelTextView.text = category
            getStartedLabel.text = getString(R.string.get_started_text, category)
            featuredCoursesLabel.text = getString(R.string.featured_courses, category)
            popularCoursesLabel.text = getString(R.string.popular_courses, category)
        }





    }


    private fun setupGetStartedCourses() {
        val getStartedAdapter = CourseHorizontalAdapter()
        binding.getStartedCoursesRecyclerView.adapter = getStartedAdapter
        binding.getStartedCoursesRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)


        viewModel.getStartedCategory.observe(viewLifecycleOwner, {
            getStartedAdapter.submitList(it)
        })

        getStartedAdapter.setOnItemClickListener {
            findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToDetailFragment(it))
        }

        viewModel.getStartedCategoryStatus.observe(viewLifecycleOwner, { status ->

            when (status) {
                NetworkResponseStatus.LOADING -> {
                    binding.apply {
                        getStartedCoursesRecyclerView.visibility = View.INVISIBLE
                        getStartedCoursesProgressBar.visibility = View.VISIBLE
                    }
                }

                NetworkResponseStatus.DONE -> {
                    binding.apply {
                        getStartedCoursesRecyclerView.visibility = View.VISIBLE
                        getStartedCoursesProgressBar.visibility = View.INVISIBLE
                    }
                }

                else -> {
                    binding.apply {
                        getStartedCoursesRecyclerView.visibility = View.INVISIBLE
                        getStartedCoursesProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun setupFeaturedCourses() {

        val featuredCoursesAdapter = CourseHorizontalAdapter()
        binding.featuredCoursesRecyclerView.adapter = featuredCoursesAdapter
        binding.featuredCoursesRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        viewModel.featuredCategory.observe(viewLifecycleOwner, {
            featuredCoursesAdapter.submitList(it)
        })

        featuredCoursesAdapter.setOnItemClickListener {
            findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToDetailFragment(it))
        }

        viewModel.featuredCategoryStatus.observe(viewLifecycleOwner, { status ->

            when (status) {
                NetworkResponseStatus.LOADING -> {
                    binding.apply {
                        featuredCoursesRecyclerView.visibility = View.INVISIBLE
                        featuredCoursesProgressBar.visibility = View.VISIBLE
                    }
                }

                NetworkResponseStatus.DONE -> {
                    binding.apply {
                        featuredCoursesRecyclerView.visibility = View.VISIBLE
                        featuredCoursesProgressBar.visibility = View.INVISIBLE
                    }
                }

                else -> {
                    binding.apply {
                        featuredCoursesRecyclerView.visibility = View.INVISIBLE
                        featuredCoursesProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun setupPopularCourses() {

        val popularCoursesAdapter = CourseHorizontalAdapter()
        binding.popularCoursesRecyclerView.adapter = popularCoursesAdapter
        binding.popularCoursesRecyclerView.layoutManager = LinearLayoutManager(context, RecyclerView.HORIZONTAL, false)

        viewModel.popularCategory.observe(viewLifecycleOwner, {
            popularCoursesAdapter.submitList(it)
        })

        popularCoursesAdapter.setOnItemClickListener {
            findNavController().navigate(CategoriesFragmentDirections.actionCategoriesFragmentToDetailFragment(it))
        }

        viewModel.popularCategoryStatus.observe(viewLifecycleOwner, { status ->
            when (status) {
                NetworkResponseStatus.LOADING -> {
                    binding.apply {
                        popularCoursesRecyclerView.visibility = View.INVISIBLE
                        popularCoursesProgressBar.visibility = View.VISIBLE
                    }
                }

                NetworkResponseStatus.DONE -> {
                    binding.apply {
                        popularCoursesRecyclerView.visibility = View.VISIBLE
                        popularCoursesProgressBar.visibility = View.INVISIBLE
                    }
                }

                else -> {
                    binding.apply {
                        popularCoursesRecyclerView.visibility = View.INVISIBLE
                        popularCoursesProgressBar.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

}