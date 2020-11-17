package com.example.oketestapp.viewModel

import androidx.databinding.Observable
import androidx.databinding.Observable.OnPropertyChangedCallback
import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.interactor.SearchUseCase
import com.example.domain.model.Show
import io.reactivex.rxjava3.disposables.CompositeDisposable

class SearchViewModel(private val searchUseCase: SearchUseCase) : ViewModel() {

    private val disposables = CompositeDisposable()

    private val searchResultLiveData = MutableLiveData<List<Show>>()
    private val errorsLiveData = MutableLiveData<Throwable>()

    @Suppress("UNCHECKED_CAST")
    val searchObservableField = ObservableField<String>().apply {
        addOnPropertyChangedCallback(object : OnPropertyChangedCallback() {
            override fun onPropertyChanged(sender: Observable?, propertyId: Int) {
                val searchString = (sender as ObservableField<String>).get()

                if(searchString != null)
                    search(searchString)
            }
        })
    }

    private fun search(searchString: String){
        if(searchString.length > 3){
            searchUseCase.invoke(searchString).subscribe(
                { result -> searchResultLiveData.postValue(result) },
                { error -> errorsLiveData.postValue(error) }
            ).let {
                disposables.add(it)
            }
        }
    }

    fun clearDisposables(){
        disposables.clear()
    }

    fun getSearchResultLiveData() = searchResultLiveData as LiveData<List<Show>>
    fun getErrorsLiveData() = errorsLiveData as LiveData<Throwable>

}