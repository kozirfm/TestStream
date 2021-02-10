package com.example.teststream.view.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.teststream.R
import com.example.teststream.app.App
import com.example.teststream.data.Post
import com.example.teststream.view.Data
import com.example.teststream.view.Error
import com.example.teststream.view.Loading
import com.example.teststream.view.adapters.MainRecyclerViewAdapter
import com.example.teststream.viewmodels.MainViewModel
import com.example.teststream.viewmodels.ViewModelFactory
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var mainViewModel: MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.appComponent.inject(this)
        initView()
        mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        val intent = Intent(applicationContext, DetailActivity::class.java)

        toolbar.setOnMenuItemClickListener {
            when (it.itemId) {
                R.id.activityMainMenuRefresh -> {
                    mainViewModel.getData()
                    return@setOnMenuItemClickListener true
                }
                else -> return@setOnMenuItemClickListener false
            }

        }

        val adapter = MainRecyclerViewAdapter { post ->
            startActivity(intent, post)
        }

        initRecyclerView(adapter)

        mainViewModel.viewState.observe(this) { viewState ->
            when (viewState) {
                is Loading -> showLoading()
                is Data<*> -> {
                    viewState.data?.let { adapter.posts = it as List<Post> }
                    showData()
                }
                is Error -> Toast.makeText(
                    applicationContext,
                    getString(R.string.error),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }

    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    private fun showData() {
        progressBar.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    private fun startActivity(intent: Intent, post: Post) {
        startActivity(intent.apply {
            putExtra(DetailActivity.POST, post)
        })
    }

    private fun initView() {
        progressBar = findViewById(R.id.mainActivityProgressBar)
        toolbar = findViewById(R.id.mainActivityToolbar)
    }

    private fun initRecyclerView(adapter: MainRecyclerViewAdapter) {
        recyclerView = findViewById(R.id.mainActivityRecyclerView)
        recyclerView.layoutManager =
            LinearLayoutManager(applicationContext, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = adapter
    }

    override fun onBackPressed() {
        finishAfterTransition()
    }

}