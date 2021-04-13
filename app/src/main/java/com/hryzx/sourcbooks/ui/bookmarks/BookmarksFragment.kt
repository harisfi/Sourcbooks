package com.hryzx.sourcbooks.ui.bookmarks

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.hryzx.sourcbooks.R
import com.hryzx.sourcbooks.adapter.RecyclerViewGridAdapter
import com.hryzx.sourcbooks.databinding.FragmentBookmarksBinding
import com.hryzx.sourcbooks.models.Book
import com.hryzx.sourcbooks.ui.BookDetailActivity

class BookmarksFragment : Fragment() {
//    private lateinit var bookmarksViewModel: BookmarksViewModel

    private var _binding: FragmentBookmarksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentBookmarksBinding.inflate(inflater, container, false)

        /*bookmarksViewModel = ViewModelProvider(this).get(BookmarksViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_bookmarks, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        bookmarksViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })*/

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val list = arrayListOf<Book>()
        for (i in 1..10) {
            val book = Book()
            book.title = "Heaven, zen and a boundless state of vision.$i"
            book.cover = R.drawable.sample_book
            book.writer = "Pol.$i"
            book.numPages = 81 + i
            list.add(book)
        }

        binding.rvBookmarks.layoutManager = GridLayoutManager(view.context, 2)
        val recyclerViewGridAdapter = RecyclerViewGridAdapter(list)
        binding.rvBookmarks.adapter = recyclerViewGridAdapter

        recyclerViewGridAdapter.setOnItemClickCallback(object : RecyclerViewGridAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Book) {
                val intent = Intent(activity, BookDetailActivity::class.java)
                intent.putExtra(BookDetailActivity.EXTRA_BOOK, data)
                startActivity(intent)
            }
        })
    }
}