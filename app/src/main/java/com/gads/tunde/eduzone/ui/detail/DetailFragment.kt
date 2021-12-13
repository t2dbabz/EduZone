package com.gads.tunde.eduzone.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import coil.load
import com.gads.tunde.eduzone.R
import com.gads.tunde.eduzone.databinding.FragmentDetailBinding


class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

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

    }




}