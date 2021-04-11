package com.hryzx.sourcbooks.ui.mylibrary

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

class MyLibraryFragment : Fragment() {

    /*companion object {
        fun newInstance() = MyLibraryFragment()
    }

    private lateinit var viewModel: MyLibraryViewModel*/

    private var _binding: FragmentMyLibraryBinding? = null
    private val binidng get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        return inflater.inflate(R.layout.fragment_my_library, container, false)
        _binding = FragmentMyLibraryBinding.inflate(inflater, container, false)
        return binidng.root
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

        binidng.rvMyLib.layoutManager = GridLayoutManager(view.context, 2)
        val recyclerViewGridAdapter = RecyclerViewGridAdapter(list)
        binidng.rvMyLib.adapter = recyclerViewGridAdapter
    }

}