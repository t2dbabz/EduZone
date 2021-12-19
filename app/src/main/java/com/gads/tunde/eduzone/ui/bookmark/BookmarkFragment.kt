package com.gads.tunde.eduzone.ui.bookmark

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gads.tunde.eduzone.R
import com.gads.tunde.eduzone.adapter.CoursesAdapter
import com.gads.tunde.eduzone.databinding.FragmentBookmarkBinding


class BookmarkFragment : Fragment() {
    private lateinit var binding: FragmentBookmarkBinding

    private val viewModel: BookmarkViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, BookmarkViewModelFactory(activity.application)).get(BookmarkViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CoursesAdapter()

        binding.bookmarkRecyclerView.adapter = adapter

        viewModel.bookmarkedCourses.observe(viewLifecycleOwner, Observer { bookmarkedCoursesList ->

            if (bookmarkedCoursesList.isNotEmpty()) {
                adapter.submitList(bookmarkedCoursesList)
            } else {
                binding.bookmarkRecyclerView.visibility = View.INVISIBLE
                binding.emptyStateImage.visibility = View.VISIBLE
                binding.emptyStateText.visibility = View.VISIBLE
            }

        })

        adapter.setOnItemClickListener { bookmarkedCourse ->
            findNavController().navigate(BookmarkFragmentDirections.actionBookmarkFragmentToDetailFragment(bookmarkedCourse))
        }

    }

}