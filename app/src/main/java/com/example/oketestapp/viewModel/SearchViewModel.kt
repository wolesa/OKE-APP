package com.example.oketestapp.viewModel

import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.interactor.SearchUseCase
import com.example.domain.model.Show

class SearchViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {

    private val searchResultLiveData = MutableLiveData<List<Show>>()

    @Suppress("UNCHECKED_CAST")
    val searchObservableField = ObservableField<String>().apply {
        addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val searchString = (sender as ObservableField<String>).get()

                if(searchString?.length ?: 0 > 3){
                    searchUseCase.invoke(searchString).subscribe {
                        searchResultLiveData.postValue(it)
                    }
                }

            }
        })
    }


    fun getSearchResultLiveData() = searchResultLiveData as LiveData<List<Show>>

}
