package com.example.teststream.view

sealed class ViewState
object Loading : ViewState()
data class Data<T>(val data: T) : ViewState()
data class Error(val t: Throwable?) : ViewState()