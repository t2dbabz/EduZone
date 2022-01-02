package com.gads.tunde.eduzone.ui.search

import android.content.Context
import android.os.Bundle
import android.view.*
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gads.tunde.eduzone.R
import com.gads.tunde.eduzone.adapter.CoursesAdapter
import com.gads.tunde.eduzone.databinding.FragmentSearchBinding
import com.gads.tunde.eduzone.model.Course
import com.google.android.material.bottomnavigation.BottomNavigationView

class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding

    private val viewModel: SearchViewModel by lazy {
        val activity = requireNotNull(this.activity) {
        }
        ViewModelProvider(this, SearchViewModelFactory(activity.application))[SearchViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        (activity as AppCompatActivity).supportActionBar?.show()
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CoursesAdapter()

        binding.searchRecyclerView.adapter = adapter

        viewModel.searchResultList.observe(viewLifecycleOwner, { searchResult ->

            if (searchResult != null) {
                adapter.submitList(searchResult)
                binding.searchStateImageView.visibility = View.INVISIBLE
                binding.searchingText.visibility = View.INVISIBLE
            }
        })

        adapter.setOnItemClickListener {
            findNavController().navigate(SearchFragmentDirections.actionSearchFragmentToDetailFragment(it))
        }






    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.search_menu, menu)
         val searchItem = menu.findItem(R.id.actionSearch)
        val searchView = searchItem.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.isSubmitButtonEnabled = true
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null ) {
                    viewModel.searchCourses(query)
                }

                hideKeyboard()
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                binding.searchStateImageView.visibility = View.INVISIBLE
                binding.searchingText.visibility = View.INVISIBLE
                if (query != null ) {
                    viewModel.searchCourses(query)
                }
                return true
            }

        })

        searchView.setOnQueryTextFocusChangeListener { view, hasFocus ->
            val bottomNav =  (activity as AppCompatActivity).findViewById<BottomNavigationView>(R.id.bottomView_navigation)
            if (hasFocus) {
                bottomNav.visibility = View.GONE
            } else {
                bottomNav.visibility = View.VISIBLE
            }
        }

    }

    private fun hideKeyboard() {
        val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(requireView().windowToken, 0)
    }


}