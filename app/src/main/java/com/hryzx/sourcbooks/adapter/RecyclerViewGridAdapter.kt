package com.hryzx.sourcbooks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hryzx.sourcbooks.R
import com.hryzx.sourcbooks.databinding.ItemBookGridBinding
import com.hryzx.sourcbooks.models.Book
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class RecyclerViewGridAdapter(private val listBook: ArrayList<Book>) : RecyclerView.Adapter<RecyclerViewGridAdapter.GridViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridViewHolder {
        val binding = ItemBookGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        holder.bind(listBook[position])
    }

    override fun getItemCount(): Int = listBook.size

    inner class GridViewHolder(private val binding: ItemBookGridBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            Glide.with(itemView.context)
                    .load(book.cover)
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_placehold))
                    .error(R.drawable.ic_error)
                    .into(binding.imgCoverGrid)
            itemView.setOnClickListener { onItemClickCallback?.onItemClicked(book) }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Book)
    }
}