package com.example.teststream.view.activities

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.bumptech.glide.Glide
import com.example.teststream.R
import com.example.teststream.data.Post

class DetailActivity : AppCompatActivity() {

    companion object {
        val POST: String = DetailActivity::class.java.simpleName
    }

    lateinit var image: ImageView
    lateinit var description: TextView
    lateinit var toolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initView()
        val post = intent.getParcelableExtra<Post>(POST)
        toolbar.setNavigationOnClickListener {
            finish()
        }

        toolbar.title = post?.location
        Glide.with(applicationContext)
            .load(post?.image)
            .into(image)
        description.text = post?.description ?: getString(R.string.error)

    }

    private fun initView() {
        toolbar = findViewById(R.id.detailActivityToolbar)
        image = findViewById(R.id.activityDetailImageView)
        description = findViewById(R.id.activityDetailDescriptionTextView)
    }

}