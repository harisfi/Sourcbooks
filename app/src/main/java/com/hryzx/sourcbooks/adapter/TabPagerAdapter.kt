package com.hryzx.sourcbooks.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private var tabFragments: ArrayList<Fragment> = ArrayList()
    private var tabTitles: ArrayList<String> = ArrayList()

    override fun getCount(): Int = tabFragments.size
    override fun getItem(position: Int): Fragment = tabFragments[position]
    override fun getPageTitle(position: Int): CharSequence = tabTitles[position]

    fun addFrag(fragment: Fragment, title: String) {
        tabFragments.add(fragment)
        tabTitles.add(title)
    }
}