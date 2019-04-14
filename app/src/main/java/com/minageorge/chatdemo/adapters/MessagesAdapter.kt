package com.minageorge.chatdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.minageorge.chatdemo.R
import com.minageorge.chatdemo.store.models.messages.MessagesEntity
import com.minageorge.chatdemo.store.models.rooms.RoomsEntity
import kotlinx.android.synthetic.main.item_message.view.*

class MessagesAdapter : RecyclerView.Adapter<MessagesAdapter.ViewHolder>() {

    private val messages = ArrayList<MessagesEntity>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_message, parent, false))
    }

    override fun getItemCount() = messages.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(messages[position])
    }

    fun swipData(newItems: List<MessagesEntity>) {
        messages.clear()
        messages.addAll(newItems)
        notifyDataSetChanged()
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        private val message = v.tv_message

        fun bind(msg: MessagesEntity) {
            if (msg.user_id.toInt() == 2) {
                message.isSelected = true
                message.layoutParams.resolveLayoutDirection(View.LAYOUT_DIRECTION_RTL)
            }else{
                message.isSelected = false
                message.layoutParams.resolveLayoutDirection(View.LAYOUT_DIRECTION_LTR)
            }
            message.text = msg.message
        }
    }
}