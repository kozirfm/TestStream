package com.example.teststream.datasource

import com.example.teststream.data.Root
import com.example.teststream.model.retrofit.RetrofitService
import io.reactivex.rxjava3.core.Single
import retrofit2.Response

class RemoteDataSourceImplementation(private val api: RetrofitService) :
    RemoteDataSource {

    override fun getPosts(page: Int): Single<Response<Root>> {
        return api.getPosts(page)
    }

}