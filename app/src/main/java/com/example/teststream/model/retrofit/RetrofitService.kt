package com.example.teststream.model.retrofit

import com.example.teststream.data.Root
import io.reactivex.rxjava3.core.Single
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {
    @GET("search?media_type=image")
    fun getPosts(@Query("page") page: Int): Single<Response<Root>>
}