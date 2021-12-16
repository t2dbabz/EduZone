package com.gads.tunde.eduzone.ui.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.gads.tunde.eduzone.R
import com.gads.tunde.eduzone.adapter.CoursesAdapter
import com.gads.tunde.eduzone.databinding.FragmentSearchBinding
import com.gads.tunde.eduzone.model.Course

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
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = CoursesAdapter()

        binding.searchRecyclerView.adapter = adapter

        viewModel.searchResultList.observe(viewLifecycleOwner, Observer { searchResult ->
            if (searchResult != null) {
                adapter.submitList(searchResult)
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
                searchView.onActionViewCollapsed()
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query != null ) {
                    viewModel.searchCourses(query)
                }
                return true
            }

        })

    }


}