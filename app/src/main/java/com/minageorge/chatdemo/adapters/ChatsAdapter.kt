package com.minageorge.chatdemo.adapters

import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minageorge.chatdemo.R
import com.minageorge.chatdemo.store.models.rooms.RoomsEntity
import com.minageorge.chatdemo.ui.chat.ChatActivity
import kotlinx.android.synthetic.main.item_chat.view.*

class ChatsAdapter : RecyclerView.Adapter<ChatsAdapter.ViewHolder>() {

    private val items = ArrayList<RoomsEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_chat, parent, false))
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun swipData(newItems: List<RoomsEntity>) {
        items.clear()
        items.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val time = v.latest_time
        private val name = v.my_status
        private var item: RoomsEntity? = null

        init {
            v.setOnClickListener {
                val intent = Intent(v.context, ChatActivity::class.java)
                intent.putExtra("room_id", item!!.id)
                v.context.startActivity(intent)
            }
        }

        fun bind(item: RoomsEntity) {
            this.item = item
            Log.d("romeName: ${item.room_name}","romeId: ${item.id}")
            time.text = item.last_date!!.time.toString()
            name.text = item.room_name
        }
    }
}