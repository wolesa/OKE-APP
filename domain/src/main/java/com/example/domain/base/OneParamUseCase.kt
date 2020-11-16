package com.example.domain.base

import io.reactivex.rxjava3.core.Observable

interface OneParamUseCase<T: Any?, R: Any> {
    operator fun invoke(t: T): Observable<R>
}