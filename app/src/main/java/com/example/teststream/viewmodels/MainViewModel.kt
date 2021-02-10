package com.example.teststream.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.teststream.model.interactors.Interactor
import com.example.teststream.view.Error
import com.example.teststream.view.Loading
import com.example.teststream.view.ViewState
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

class MainViewModel @Inject constructor(private val interactor: Interactor) : ViewModel() {

    private val compositeDisposable = CompositeDisposable()
    val viewState = MutableLiveData<ViewState>()

    init {
        getData()
    }

    fun getData() {
        viewState.postValue(Loading)
        val disposable = interactor.getPosts().subscribe({ result ->
            viewState.postValue(result)
        }, { throwable ->
            viewState.postValue(Error(throwable))
        })
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}