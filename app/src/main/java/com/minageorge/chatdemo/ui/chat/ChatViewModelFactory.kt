package com.minageorge.chatdemo.ui.chat

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.minageorge.chatdemo.store.ChatDataBase

class ChatViewModelFactory(private val chatDataBase: ChatDataBase):ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return ChatViewModel(chatDataBase) as T
    }
}