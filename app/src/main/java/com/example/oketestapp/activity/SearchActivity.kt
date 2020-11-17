package com.example.oketestapp.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
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

        initErrorObserver()
    }

    private fun initDataBinding(){
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search)

        binding?.apply {
            lifecycleOwner = this@SearchActivity
            viewModel = searchViewModel
        }
    }

    private fun initShowsList() {
        binding?.rvResults?.apply {
            adapter = showsListAdapter
            layoutManager = listLayoutManager
        }

        searchViewModel.getSearchResultLiveData().observe(this, Observer {
            showsListAdapter.submitList(it)
        })
    }

    private fun initErrorObserver(){
        searchViewModel.getErrorsLiveData().observe(this, Observer {
            showError(it)
        })
    }

    private fun showError(throwable: Throwable?){
        AlertDialog.Builder(this)
            .apply {
                title = "Error"
                setMessage(throwable?.message)
                setCancelable(false)
                setNeutralButton("Ok", { dialogInterface, i ->  dialogInterface.dismiss()})
            }.show()
    }

    override fun onPause() {
        super.onPause()

        searchViewModel.clearDisposables()
    }

    override fun onStop() {
        super.onStop()

        searchViewModel.clearDisposables()
    }


}