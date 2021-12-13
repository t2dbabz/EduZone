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

        val adapter = CoursesAdapter()
        binding.recyclerView.adapter = adapter

        viewModel.coursesList.observe(viewLifecycleOwner, Observer {
            adapter.submitList(it)
        })

        adapter.setOnItemClickListener {  selectedCourse ->
            findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToDetailFragment(selectedCourse))
        }

    }


}