package com.hryzx.sourcbooks.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hryzx.sourcbooks.R
import com.hryzx.sourcbooks.models.Book

class RecyclerViewListAdapter(private val listBook: ArrayList<Book>) : RecyclerView.Adapter<RecyclerViewListAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewListAdapter.ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_book_list, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewListAdapter.ListViewHolder, position: Int) {
        val book = listBook[position]

        Glide.with(holder.itemView.context)
                .load(book.cover)
                .into(holder.imgCover)

        holder.txTitle.text = book.title
        holder.txWriter.text = "by ${book.writer}"
//        val numPage = "${book.numPages} pages"
        holder.txNumPage.text = "${book.numPages} pages"
        holder.ratingBook.rating = 2.5F

        holder.imgBookmark.setOnClickListener {
            onItemClickCallback.onItemClicked(listBook.get(holder.adapterPosition))
        }

        holder.txTitle.setOnClickListener {
            Toast.makeText(
                holder.itemView.context,
                book.title,
                Toast.LENGTH_SHORT
            ).show()
        }
        holder.imgBookmark.setOnClickListener {
            holder.imgBookmark.setImageResource(if (book.bookmarked) R.drawable.ic_bookmark_border_24 else R.drawable.ic_bookmark_24)
            book.bookmarked = !book.bookmarked
        }
    }

    override fun getItemCount(): Int {
        return listBook.size
    }

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgCover: ImageView = itemView.findViewById(R.id.img_cover)
        var txTitle: TextView = itemView.findViewById(R.id.txTitle)
        var txWriter: TextView = itemView.findViewById(R.id.txWriter)
        var txNumPage: TextView = itemView.findViewById(R.id.txNumPage)
        var imgBookmark: ImageButton = itemView.findViewById(R.id.img_bookmark)
        var ratingBook: RatingBar = itemView.findViewById(R.id.rating)
    }

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Book)
    }
}