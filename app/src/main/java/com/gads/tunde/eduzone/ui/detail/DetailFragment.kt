package com.gads.tunde.eduzone.ui.detail

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.gads.tunde.eduzone.R
import com.gads.tunde.eduzone.database.DatabaseCourse
import com.gads.tunde.eduzone.databinding.FragmentDetailBinding
import com.gads.tunde.eduzone.model.Course
import com.google.android.material.snackbar.Snackbar


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    private val viewModel: DetailViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, DetailViewModelFactory(activity.application))[DetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedCourse = DetailFragmentArgs.fromBundle(requireArguments()).selectedCourse

        binding.courseDetailTitle.text = selectedCourse.title
        binding.courseDetailDescription.text = selectedCourse.description
        binding.courseDetailImage.load(selectedCourse.image)
        binding.courseDetailPrice.text = getString(R.string.course_price, selectedCourse.price)
        binding.courseDetailInstructor.text = getString(R.string.course_creator, selectedCourse.instructor)
        binding.instructorProfileImageView.load(selectedCourse. instructorImage)
        binding.instructorNameTextView.text = selectedCourse.instructor
        binding.instructorTitle.text = selectedCourse.instructorTitle
        bookmarkCheck(selectedCourse)


        binding.startCourseButton.setOnClickListener {
            val url = selectedCourse.url
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("https://www.udemy.com$url")
            startActivity(intent)
        }

        binding.addBookmarkButton.setOnClickListener {
            val bookmarkedCourse = DatabaseCourse(
                id = selectedCourse.id,
                title = selectedCourse.title,
                description = selectedCourse.description,
                price = selectedCourse.price,
                image = selectedCourse.image,
                url = selectedCourse.url,
                instructor = selectedCourse.instructor,
                instructorTitle = selectedCourse.instructorTitle,
                instructorImage = selectedCourse.instructorImage,
                isBookmarked = true
            )

            viewModel.updateCourse(bookmarkedCourse)

            Snackbar.make(requireActivity().findViewById(R.id.scrollView),
                "Added to Bookmark",
                Snackbar.LENGTH_SHORT).show()

            binding.addBookmarkButton.isEnabled = false
        }
    }

    private fun bookmarkCheck(course: Course) {
        when(course.isBookmarked) {
            true -> {
                binding.addBookmarkButton.isEnabled = false
                binding.addBookmarkButton.text = getString(R.string.bookmarked)
            }
        }
    }

}

