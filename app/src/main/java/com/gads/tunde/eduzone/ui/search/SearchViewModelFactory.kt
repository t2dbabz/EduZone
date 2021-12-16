package com.gads.tunde.eduzone.ui.search

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException

class SearchViewModelFactory(val app: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            @Suppress("UNCHECK CAST")
            return  SearchViewModel(app) as T
        }
        throw  IllegalArgumentException()
    }

}