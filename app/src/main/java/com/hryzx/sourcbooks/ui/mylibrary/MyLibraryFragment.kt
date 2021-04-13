package com.hryzx.sourcbooks.ui.mylibrary

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.hryzx.sourcbooks.R
import com.hryzx.sourcbooks.adapter.RecyclerViewGridAdapter
import com.hryzx.sourcbooks.databinding.FragmentMyLibraryBinding
import com.hryzx.sourcbooks.models.Book
import com.hryzx.sourcbooks.ui.BookDetailActivity

class MyLibraryFragment : Fragment() {

    /*companion object {
        fun newInstance() = MyLibraryFragment()
    }

    private lateinit var viewModel: MyLibraryViewModel*/

    private var _binding: FragmentMyLibraryBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_my_library, container, false)
        _binding = FragmentMyLibraryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        viewModel = ViewModelProvider(this).get(MyLibraryViewModel::class.java)

        val list = arrayListOf<Book>()
        for (i in 1..10) {
            val book = Book()
            book.title = "Jacks are the parrots of the wet pestilence.$i"
            book.cover = R.drawable.sample_book
            book.writer = "Pol.$i"
            book.numPages = 81 + i
            list.add(book)
        }

        binding.rvMyLib.layoutManager = GridLayoutManager(view.context, 2)
        val recyclerViewGridAdapter = RecyclerViewGridAdapter(list)
        binding.rvMyLib.adapter = recyclerViewGridAdapter

        recyclerViewGridAdapter.setOnItemClickCallback(object : RecyclerViewGridAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Book) {
                val intent = Intent(activity, BookDetailActivity::class.java)
                intent.putExtra(BookDetailActivity.EXTRA_BOOK, data)
                startActivity(intent)
            }
        })
    }

}