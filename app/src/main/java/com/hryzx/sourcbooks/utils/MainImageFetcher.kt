package com.hryzx.sourcbooks.utils

import android.content.Intent
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.hryzx.sourcbooks.R
import com.hryzx.sourcbooks.models.Book
import com.hryzx.sourcbooks.ui.BookDetailActivity
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject

class MainImageFetcher constructor(val subject: String, val view: View, val img1: ImageView, val img2: ImageView) {
    private fun setImage(books: ArrayList<Book>) {
        Glide.with(view)
            .load("http://covers.openlibrary.org/b/id/${books[0].cover}-M.jpg")
            .apply(RequestOptions.placeholderOf(R.drawable.ic_placehold))
            .error(R.drawable.ic_error)
            .into(img1)
        Glide.with(view)
            .load("http://covers.openlibrary.org/b/id/${books[1].cover}-M.jpg")
            .apply(RequestOptions.placeholderOf(R.drawable.ic_placehold))
            .error(R.drawable.ic_error)
            .into(img2)

        img1.setOnClickListener {
            val intent = Intent(view.context, BookDetailActivity::class.java)
            intent.putExtra(BookDetailActivity.EXTRA_BOOK, books[0])
            view.context.startActivity(intent)
        }

        img2.setOnClickListener {
            val intent = Intent(view.context, BookDetailActivity::class.java)
            intent.putExtra(BookDetailActivity.EXTRA_BOOK, books[1])
            view.context.startActivity(intent)
        }
    }

    fun fetch() {
        val listItems = ArrayList<Book>()
        val url = "https://openlibrary.org/subjects/$subject.json?limit=2"

        val client = AsyncHttpClient()
        client.get(url, object : AsyncHttpResponseHandler() {
            override fun onSuccess(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray) {
                try {
                    val result = String(responseBody)
                    val responseObject = JSONObject(result)
                    val list = responseObject.getJSONArray("works")

                    for (i in 0 until list.length()) {
                        val item = list.getJSONObject(i)
                        val bookItem = Book()
                        val author = item.getJSONArray("authors")
                            .getJSONObject(0)
                            .getString("name")

                        with(bookItem) {
                            key = item.getString("key")
                            title = item.getString("title")
                            if (!item.isNull("cover_id")) {
                                cover = item.getInt("cover_id")
                            }
                            writer = author
                        }
                        listItems.add(bookItem)
                    }
                    setImage(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }

        })
    }
}