package com.hryzx.sourcbooks.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.hryzx.sourcbooks.ui.MainActivity.Companion.tabTitles
import com.hryzx.sourcbooks.R
import com.hryzx.sourcbooks.adapter.RecyclerViewListAdapter
import com.hryzx.sourcbooks.adapter.TabPagerAdapter
import com.hryzx.sourcbooks.databinding.FragmentHomeBinding
import com.hryzx.sourcbooks.models.Book
import com.hryzx.sourcbooks.ui.home.item1.HomeItem1Fragment
import com.hryzx.sourcbooks.ui.home.item2.HomeItem2Fragment
import com.hryzx.sourcbooks.ui.home.item3.HomeItem3Fragment
import com.hryzx.sourcbooks.ui.home.item4.HomeItem4Fragment

class HomeFragment : Fragment() {
//    private lateinit var homeViewModel: HomeViewModel

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = TabPagerAdapter(childFragmentManager)

        adapter.addFrag(HomeItem1Fragment(), tabTitles[0])
        adapter.addFrag(HomeItem2Fragment(), tabTitles[1])
        adapter.addFrag(HomeItem3Fragment(), tabTitles[2])
        adapter.addFrag(HomeItem4Fragment(), tabTitles[3])

        binding.viewpagerMain.adapter = adapter
        binding.tabsMain.setupWithViewPager(binding.viewpagerMain)

        val list = arrayListOf<Book>()
        for (i in 1..5) {
            val book = Book()
            book.title = "A falsis, itineris tramitem placidus onus.$i"
            book.cover = R.drawable.sample_book
            book.writer = "Pol.$i"
            book.numPages = 981 + i
            list.add(book)
        }

        binding.rvHomeBooks.layoutManager = LinearLayoutManager(view.context)
        val mainRecyclerViewAdapter = RecyclerViewListAdapter(list)
        binding.rvHomeBooks.adapter = mainRecyclerViewAdapter

//        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        /*homeViewModel.text.observe(viewLifecycleOwner, {
            adapter.addFrag(HomeItem1Fragment(), it[0])
            adapter.addFrag(HomeItem2Fragment(), it[1])
            adapter.addFrag(HomeItem3Fragment(), it[2])
            adapter.addFrag(HomeItem4Fragment(), it[3])
        })*/
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}