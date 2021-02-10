package com.example.teststream.model.interactors

import com.example.teststream.view.ViewState
import io.reactivex.rxjava3.core.Single

interface Interactor {
    fun getPosts(): Single<ViewState>
}