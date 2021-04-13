package com.hryzx.sourcbooks.ui.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.hryzx.sourcbooks.ui.BookDetailActivity
import com.hryzx.sourcbooks.ui.home.item1.HomeItem1Fragment
import com.hryzx.sourcbooks.ui.home.item2.HomeItem2Fragment
import com.hryzx.sourcbooks.ui.home.item3.HomeItem3Fragment
import com.hryzx.sourcbooks.ui.home.item4.HomeItem4Fragment
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.AsyncHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONObject
import java.lang.Exception

class HomeFragment : Fragment() {
//    private lateinit var homeViewModel: HomeViewModel
    private lateinit var rvAdapter: RecyclerViewListAdapter

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

        rvAdapter = RecyclerViewListAdapter()
        rvAdapter.notifyDataSetChanged()

        binding.rvHomeBooks.layoutManager = LinearLayoutManager(view.context)
        binding.rvHomeBooks.adapter = rvAdapter
        setRecommendation()

        rvAdapter.setOnItemClickCallback(object : RecyclerViewListAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Book) {
                val intent = Intent(activity, BookDetailActivity::class.java)
                intent.putExtra(BookDetailActivity.EXTRA_BOOK, data)
                startActivity(intent)
            }
        })

//        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)


        /*homeViewModel.text.observe(viewLifecycleOwner, {
            adapter.addFrag(HomeItem1Fragment(), it[0])
            adapter.addFrag(HomeItem2Fragment(), it[1])
            adapter.addFrag(HomeItem3Fragment(), it[2])
            adapter.addFrag(HomeItem4Fragment(), it[3])
        })*/
    }

    private fun setRecommendation() {
        val listItems = ArrayList<Book>()
        val url = "https://openlibrary.org/subjects/painting__paintings.json?limit=7"

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
                    rvAdapter.setData(listItems)
                } catch (e: Exception) {
                    Log.d("Exception", e.message.toString())
                }
            }

            override fun onFailure(statusCode: Int, headers: Array<out Header>?, responseBody: ByteArray?, error: Throwable) {
                Log.d("onFailure", error.message.toString())
            }

        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}