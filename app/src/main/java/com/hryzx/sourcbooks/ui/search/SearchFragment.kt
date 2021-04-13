package com.hryzx.sourcbooks.ui.search

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hryzx.sourcbooks.R
import com.hryzx.sourcbooks.adapter.RecyclerViewListAdapter
import com.hryzx.sourcbooks.databinding.FragmentSearchBinding
import com.hryzx.sourcbooks.models.Book
import com.hryzx.sourcbooks.ui.BookDetailActivity

class SearchFragment : Fragment() {
//    private lateinit var searchViewModel: SearchViewModel

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        /*searchViewModel = ViewModelProvider(this).get(SearchViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_search, container, false)
        val textView: TextView = root.findViewById(R.id.text_dashboard)
        searchViewModel.text.observe(viewLifecycleOwner, Observer {
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

        /*binding.rvSearchBooks.layoutManager = LinearLayoutManager(view.context)
        val mainRecyclerViewAdapter = RecyclerViewListAdapter(list)
        binding.rvSearchBooks.adapter = mainRecyclerViewAdapter

        mainRecyclerViewAdapter.setOnItemClickCallback(object : RecyclerViewListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Book) {
                val intent = Intent(activity, BookDetailActivity::class.java)
                intent.putExtra(BookDetailActivity.EXTRA_BOOK, data)
                startActivity(intent)
            }
        })*/
    }
}