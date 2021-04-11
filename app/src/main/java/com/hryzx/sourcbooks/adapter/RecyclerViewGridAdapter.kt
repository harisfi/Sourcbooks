package com.hryzx.sourcbooks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hryzx.sourcbooks.R
import com.hryzx.sourcbooks.models.Book

class RecyclerViewGridAdapter(private val listBook: ArrayList<Book>) : RecyclerView.Adapter<RecyclerViewGridAdapter.GridViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_book_grid, parent, false)
        return GridViewHolder(view)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        val book = listBook[position]

        Glide.with(holder.itemView.context)
                .load(book.cover)
                .into(holder.imgCover)
    }

    override fun getItemCount(): Int {
        return listBook.size
    }

    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgCover: ImageView = itemView.findViewById(R.id.img_cover_grid)
    }
}