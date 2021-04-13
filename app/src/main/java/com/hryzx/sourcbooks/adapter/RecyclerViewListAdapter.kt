package com.hryzx.sourcbooks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.hryzx.sourcbooks.R
import com.hryzx.sourcbooks.databinding.ItemBookListBinding
import com.hryzx.sourcbooks.models.Book
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class RecyclerViewListAdapter() : RecyclerView.Adapter<RecyclerViewListAdapter.ListViewHolder>() {
    private var onItemClickCallback: OnItemClickCallback? = null
    private val listBook = ArrayList<Book>()

    fun setData(items: ArrayList<Book>) {
        listBook.clear()
        listBook.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemBookListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listBook[position])
    }

    override fun getItemCount(): Int = listBook.size

    inner class ListViewHolder(private val binding: ItemBookListBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            with(binding) {
                Glide.with(itemView.context)
                        .load("http://covers.openlibrary.org/b/id/${book.cover}-M.jpg")
                        .apply(RequestOptions.placeholderOf(R.drawable.ic_placehold))
                        .error(R.drawable.ic_error)
                        .into(imgCover)

                txTitle.text = book.title
                txWriter.text = root.context.getString(R.string.by_, book.writer)
                txNumPage.text = root.context.getString(R.string.page_, book.numPages)

                imgBookmark.setOnClickListener {
                    imgBookmark.setImageResource(if (book.bookmarked) R.drawable.ic_bookmark_border_24 else R.drawable.ic_bookmark_24)
                    book.bookmarked = !book.bookmarked
                }

                itemView.setOnClickListener { onItemClickCallback?.onItemClicked(book) }
            }
        }
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Book)
    }
}