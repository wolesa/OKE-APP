package com.example.domain.di

import com.example.domain.interactor.SearchUseCase
import org.koin.dsl.module

val interactionModule = module {
    /*
    *
    * factory {..UseCase(get()) }
    *
    * */

    factory {SearchUseCase(get())}
}