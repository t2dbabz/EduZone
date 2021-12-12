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

        binding.courseDetailTitle.text = selectedCourse.courseTitle
        binding.courseDetailDescription.text = selectedCourse.courseDescription
        binding.courseDetailImage.load(selectedCourse.courseImageLarge)
        binding.courseDetailPrice.text = getString(R.string.course_price, selectedCourse.coursePrice)
        binding.courseDetailInstructor.text = getString(R.string.course_creator, selectedCourse.visible_instructors[0].title)
        binding.instructorProfileImageView.load(selectedCourse.visible_instructors[0].image_100x100)
        binding.instructorNameTextView.text = selectedCourse.visible_instructors[0].display_name
        binding.instructorTitle.text = selectedCourse.visible_instructors[0].job_title

    }




}