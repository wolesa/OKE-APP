package com.example.oketestapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.databinding.DataBindingUtil
import androidx.databinding.Observable
import androidx.databinding.ObservableField
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.oketestapp.R
import com.example.oketestapp.ShowsAdapter
import com.example.oketestapp.databinding.ActivitySearchBinding
import com.example.oketestapp.viewModel.SearchViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class SearchActivity : AppCompatActivity() {
    private val searchViewModel: SearchViewModel by viewModel()
    private var binding: ActivitySearchBinding? = null

    private val showsListAdapter = ShowsAdapter()
    private val listLayoutManager = LinearLayoutManager(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        initDataBinding()

        initShowsList()
    }

    private fun initDataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        binding?.apply {
            lifecycleOwner = this@SearchActivity
            viewModel = searchViewModel
        }
    }

    private fun initShowsList(){
        binding?.rvResults?.apply {
            adapter = showsListAdapter
            layoutManager = listLayoutManager
        }

        searchViewModel.getSearchResultLiveData().observe(this, Observer {
            showsListAdapter.submitList(it)
        })
    }
}