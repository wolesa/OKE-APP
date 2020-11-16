package com.example.domain.repository

import com.example.domain.model.Show
import io.reactivex.rxjava3.core.Observable

interface Repo {

    fun searchShows(query: String): Observable<List<Show>>

}