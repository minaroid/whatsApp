package com.minageorge.chatdemo.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.minageorge.chatdemo.ui.home.CallsFragment
import com.minageorge.chatdemo.ui.home.CameraFragment
import com.minageorge.chatdemo.ui.home.ChatsFragment
import com.minageorge.chatdemo.ui.home.StatusFragment

class HomePagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> CameraFragment.newInstance()
            1 -> ChatsFragment.newInstance()
            2 -> StatusFragment.newInstance()
            3 -> CallsFragment.newInstance()
            else -> ChatsFragment.newInstance() // default fragment
        }
    }

    override fun getCount() = 4

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> ""
            1 -> "CHATS"
            2 -> "STATUS"
            3 -> "CALLS"
            else -> super.getPageTitle(position)
        }
    }

}