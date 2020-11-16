package com.example.data.repository

import com.example.domain.model.Show
import com.example.domain.repository.Repo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class RepoImpl(private val remoteService: RemoteService): Repo {
    override fun searchShows(query: String): Observable<List<Show>> {
        return remoteService.searchShows(query)
            .subscribeOn(Schedulers.io())
            .map {
                it.map { Show(
                    name = it.show?.name ?: "",
                    genres = if(it.show?.genres?.isEmpty() != false) "" else it.show.genres.toString(),
                    imageUrl = it.show?.image?.originalImageUrl ?: ""
                ) }
            }
    }
}