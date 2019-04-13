package com.minageorge.chatdemo.ui.home

import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.minageorge.chatdemo.R
import com.minageorge.chatdemo.adapters.ChatsAdapter
import com.minageorge.chatdemo.views.SeparatorDecoration
import kotlinx.android.synthetic.main.fragment_chats.*

class ChatsFragment : Fragment() {

    private lateinit var viewModel: HomeViewModel
    private lateinit var chatsAdapter: ChatsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_chats, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val decoration = SeparatorDecoration(
            context,
            Color.parseColor("#EAEAEA"),
            0.5f
        )
        chats_recycler.setHasFixedSize(true)
        chats_recycler.layoutManager = LinearLayoutManager(context)
        chatsAdapter = ChatsAdapter()
        chats_recycler.adapter = chatsAdapter
        chats_recycler.addItemDecoration(decoration)

        super.onViewCreated(view, savedInstanceState)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        viewModel = ViewModelProviders.of(activity!!).get(HomeViewModel::class.java)
        viewModel.roomsLiveData.observe(this, Observer(chatsAdapter::swipData))
        super.onActivityCreated(savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance() = ChatsFragment()
    }
}