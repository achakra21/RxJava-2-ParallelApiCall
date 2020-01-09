package com.abhijit.rxjava_retrofit_recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.abhijit.rxjava_retrofit_recyclerview.R
import com.abhijit.rxjava_retrofit_recyclerview.data.Post

class PostAdapter(internal var context: Context,internal var postList: List<Post>):RecyclerView.Adapter<PostViewHolder> (){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_view,parent,false)
        return PostViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.textTitle.text = postList[position].title
        holder.txtDescription.text = postList[position].body
    }
}