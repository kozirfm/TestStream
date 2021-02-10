package com.example.teststream.datasource

import com.example.teststream.data.Root
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

interface RemoteDataSource {
    fun getPosts(page: Int): Single<Response<Root>>
}