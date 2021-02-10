package com.example.teststream.view.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.teststream.R
import com.example.teststream.data.Post

class MainRecyclerViewAdapter(val itemClick: ((Post) -> Unit)? = null) :
    RecyclerView.Adapter<MainRecyclerViewAdapter.MainRecyclerViewHolder>() {

    var posts = listOf<Post>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        return MainRecyclerViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_main_recycler_view, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    inner class MainRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) = with(itemView) {
            Glide.with(context)
                .load(post.image)
                .centerCrop()
                .placeholder(R.drawable.ic_twotone_image_24)
                .error(R.drawable.ic_twotone_image_not_supported_24)
                .into(findViewById(R.id.itemImageView))
            findViewById<TextView>(R.id.itemLocationTextView).text = post.location
            findViewById<ConstraintLayout>(R.id.itemConstraintLayout)
                .setOnClickListener {
                    itemClick?.invoke(post)
                }
        }
    }
}