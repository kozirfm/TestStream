package com.example.teststream.model.interactors

import com.example.teststream.data.Item
import com.example.teststream.data.Post
import com.example.teststream.datasource.RemoteDataSource
import com.example.teststream.view.Data
import com.example.teststream.view.ViewState
import io.reactivex.rxjava3.core.Single
import kotlin.random.Random

class InteractorImplementation(private val remoteDataSource: RemoteDataSource) :
    Interactor {

    override fun getPosts(): Single<ViewState> {
        return remoteDataSource.getPosts(randomPage()).flatMap { response ->
            if (response.isSuccessful) {
                val items = response.body()?.collection?.items ?: listOf()
                if (items.isEmpty()) {
                    Single.error(Throwable())
                } else {
                    Single.just(Data(mapCollection(items)))
                }
            } else {
                Single.error(Throwable())
            }
        }
    }

    private fun randomPage(): Int {
        return Random.nextInt(1, 100)
    }

    private fun mapCollection(items: List<Item>): List<Post> {
        val posts = mutableListOf<Post>()
        val locations = items.flatMap { it.data.map { data -> data.location } }
        val descriptions = items.flatMap { it.data.map { data -> data.description } }
        val images = items.flatMap { it.links.map { link -> link.href } }
        for (index in items.indices) {
            posts.add(
                Post(
                    location = locations[index],
                    description = descriptions[index],
                    image = images[index]
                )
            )
        }
        return posts
    }

}