package com.hryzx.sourcbooks.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.hryzx.sourcbooks.models.Book
import com.hryzx.sourcbooks.models.TwoDataset

class ListDetailAdapter internal constructor(private val context: Context) : BaseAdapter() {
    internal var dataset = arrayListOf<TwoDataset>()

    override fun getCount(): Int = dataset.size
    override fun getItem(position: Int): Any = dataset[position]
    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view = convertView
        if (view == null) {
            view = LayoutInflater.from(context).inflate(android.R.layout.simple_list_item_2, parent, false)
        }
        val viewHolder = ViewHolder(view as View)
        val datasets = getItem(position) as TwoDataset
        viewHolder.bind(datasets)
        return view
    }

    private inner class ViewHolder(view: View) {
        private val text1: TextView = view.findViewById(android.R.id.text1)
        private val text2: TextView = view.findViewById(android.R.id.text2)

        fun bind(twoDataset: TwoDataset) {
            text1.text = twoDataset.data1
            text2.text = twoDataset.data2
        }
    }
}