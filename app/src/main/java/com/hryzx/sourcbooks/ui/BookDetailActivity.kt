package com.hryzx.sourcbooks.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ArrayAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.appbar.AppBarLayout
import com.hryzx.sourcbooks.R
import com.hryzx.sourcbooks.adapter.ListDetailAdapter
import com.hryzx.sourcbooks.databinding.ActivityBookDetailBinding
import com.hryzx.sourcbooks.models.Book
import com.hryzx.sourcbooks.models.TwoDataset
import jp.wasabeef.glide.transformations.BlurTransformation
import jp.wasabeef.glide.transformations.CropTransformation
import jp.wasabeef.glide.transformations.RoundedCornersTransformation
import kotlin.math.abs

class BookDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityBookDetailBinding
    private lateinit var collapseMenu: Menu
    private lateinit var book: Book
    private lateinit var adapter: ListDetailAdapter

    private var appBarExpanded: Boolean = false
    private var datasets = arrayListOf<TwoDataset>()

    companion object {
        const val EXTRA_BOOK = "extra_book"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookDetailBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        setSupportActionBar(binding.animToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        book = intent.getParcelableExtra<Book>(EXTRA_BOOK) as Book
        with(binding) {
            btnSave.visibility = if (book.saved) View.GONE else View.VISIBLE
            btnRemoveSave.visibility = if (book.saved) View.VISIBLE else View.GONE

            Glide.with(this@BookDetailActivity)
                    .load("http://covers.openlibrary.org/b/id/${book.cover}-M.jpg")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_placehold))
                    .error(R.drawable.ic_error)
                    .into(imgDetail)
            Glide.with(this@BookDetailActivity)
                    .load("http://covers.openlibrary.org/b/id/${book.cover}-S.jpg")
                    .apply(RequestOptions.placeholderOf(R.drawable.ic_placehold))
                    .error(R.drawable.ic_error)
                    .apply(RequestOptions.bitmapTransform(CropTransformation(4, 4, CropTransformation.CropType.CENTER)))
                    .apply(RequestOptions.bitmapTransform(BlurTransformation(86)))
                    .into(imgBlurry)
            txTitleDetail.text = book.title
            txWriterDetail.text = getString(R.string.by_, book.writer)
            txSect1.text = book.published.toString()
            txSect2.text = book.numPages.toString()
            txSect3.text = book.revision.toString()

            adapter = ListDetailAdapter(this@BookDetailActivity)
            lvDetail.adapter = adapter
        }

        for (i in 1..5) {
            datasets.add(TwoDataset("data $i", "data 0$i\ndata 1$i\ndata 3$i"))
        }
        adapter.dataset = datasets

        var isShow = true
        var scrollRange = -1
        binding.appbar.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            if (scrollRange == -1) {
                scrollRange = appBarLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0) {
                binding.collapsingToolbar.title = book.title
                isShow = true
            } else if (isShow) {
                binding.collapsingToolbar.title = " "
                isShow = false
            }
        })
    }

    override fun onPrepareOptionsMenu(menu: Menu?): Boolean {
        if (!appBarExpanded || collapseMenu.size() != 1) {
            collapseMenu.add("Bookmark")
                .setIcon(
                    if (book.bookmarked) R.drawable.ic_bookmark_24
                    else R.drawable.ic_bookmark_border_24
                )
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
        }
        return super.onPrepareOptionsMenu(collapseMenu)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_detail, menu)
        if (menu != null) {
            this.collapseMenu = menu
        }
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.title == "Bookmark") {
            item.setIcon(
                if (book.bookmarked) R.drawable.ic_bookmark_24
                else R.drawable.ic_bookmark_border_24
            )
            book.bookmarked = !book.bookmarked
        }
        return super.onOptionsItemSelected(item)
    }
}