package com.abhijit.rxjava_retrofit_recyclerview.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.list_view.view.*

class PostViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
    val txtDescription = itemView.description
    val textTitle = itemView.title

}