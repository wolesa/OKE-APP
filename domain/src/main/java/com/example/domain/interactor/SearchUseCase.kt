package com.example.domain.interactor

import com.example.domain.base.OneParamUseCase
import com.example.domain.model.Show
import com.example.domain.repository.Repo
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.ObservableOnSubscribe

class SearchUseCase(private val repo: Repo): OneParamUseCase<String?, List<Show>> {
    override fun invoke(t: String?): Observable<List<Show>> {

        if(t == null || t.length < 3)
            return Observable.create { emitter ->
                emitter.onNext(emptyList())
                emitter.onComplete()
            }

        return repo.searchShows(t)
    }
}