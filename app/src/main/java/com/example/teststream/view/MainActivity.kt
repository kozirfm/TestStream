package com.example.teststream.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.teststream.R
import com.example.teststream.app.App
import com.example.teststream.viewmodels.MainViewModel
import com.example.teststream.viewmodels.ViewModelFactory
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        App.appComponent.inject(this)

        val mainViewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)

        mainViewModel.viewState.observe(this) { viewState ->
            when (viewState) {
                is Loading -> println("Loading")
                is Data<*> -> println(viewState.data)
                is Error -> Toast.makeText(applicationContext, "", Toast.LENGTH_SHORT).show()
            }
        }

    }

}