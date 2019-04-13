package com.minageorge.chatdemo.ui.chat

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.minageorge.chatdemo.R
import com.minageorge.chatdemo.adapters.MessagesAdapter
import kotlinx.android.synthetic.main.activity_chat.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.instance

class ChatActivity : AppCompatActivity(), KodeinAware {

    override val kodein by closestKodein()

    private val viewModelFactory: ChatViewModelFactory by instance()
    private lateinit var viewModel: ChatViewModel
    private lateinit var messagesAdapter: MessagesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chat)

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ChatViewModel::class.java)
        messagesAdapter = MessagesAdapter()
        val layoutManager = LinearLayoutManager(this)
        layoutManager.reverseLayout = true
        layoutManager.stackFromEnd = true
        rv_messages.layoutManager = layoutManager
        rv_messages.adapter = messagesAdapter
        viewModel.subscribersLiveData.observe(this, Observer {
            Log.d("ChatStarted:", "RoomId:${intent.getLongExtra("room_id", 0)}, subscribers:${it.size}")
        })

        viewModel.messagesLiveData.observe(this, Observer {
           messagesAdapter.swipData(it)
        })

        viewModel.getRoomSubscribersAndMsgs(intent.getLongExtra("room_id", 0))

        send_fab.setOnClickListener{
            if (et_message.text.toString().isNotEmpty()) {
                viewModel.insertMessage(et_message.text.toString())
                et_message.setText("")
            }
        }
    }

}
