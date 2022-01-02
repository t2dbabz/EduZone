package com.gads.tunde.eduzone.ui.bookmark

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.gads.tunde.eduzone.R
import com.gads.tunde.eduzone.adapter.CoursesAdapter
import com.gads.tunde.eduzone.database.DatabaseCourse
import com.gads.tunde.eduzone.databinding.FragmentBookmarkBinding
import com.google.android.material.snackbar.Snackbar


class BookmarkFragment : Fragment() {
    private lateinit var binding: FragmentBookmarkBinding
    private lateinit var adapter: CoursesAdapter

    private val viewModel: BookmarkViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, BookmarkViewModelFactory(activity.application)).get(BookmarkViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        // Inflate the layout for this fragment
        binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
         adapter = CoursesAdapter()

        binding.bookmarkRecyclerView.adapter = adapter

        viewModel.bookmarkedCourses.observe(viewLifecycleOwner, { bookmarkedCoursesList ->

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

       itemTouchHelper.attachToRecyclerView(binding.bookmarkRecyclerView)

    }

    private val itemTouchHelper = ItemTouchHelper(object: ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT){
        override fun onMove(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder, target: RecyclerView.ViewHolder): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val position = viewHolder.adapterPosition
            val course = adapter.getCourseAt(position)


            viewModel.deleteCourse(
                DatabaseCourse(
                    id = course.id,
                    title = course.title,
                    description = course.description,
                    price =course.price,
                    image = course.image,
                    url = course.url,
                    instructor = course.instructor,
                    instructorTitle = course.instructorTitle,
                    instructorImage = course.instructorImage,
                    isBookmarked = course.isBookmarked
                )
            )

            Snackbar.make(
                requireView(), // The ID of your coordinator_layout
                getString(R.string.course_deleted),
                Snackbar.LENGTH_LONG
            ).apply {
                setAction("UNDO") {
                    viewModel.insertCourse(
                        DatabaseCourse(
                        id = course.id,
                        title = course.title,
                        description = course.description,
                        price =course.price,
                        image = course.image,
                        url = course.url,
                        instructor = course.instructor,
                        instructorTitle = course.instructorTitle,
                        instructorImage = course.instructorImage,
                        isBookmarked = course.isBookmarked
                    ))

                    binding.bookmarkRecyclerView.scrollToPosition(position)
                }
                setActionTextColor(Color.YELLOW)
            }.show()


        }

    })





}