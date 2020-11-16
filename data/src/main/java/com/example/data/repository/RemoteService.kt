package com.example.data.repository

import com.example.data.model.ResultPojo
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface RemoteService {

    @GET("search/shows")
    fun searchShows(@Query("q") query: String) : Observable<List<ResultPojo>>

}