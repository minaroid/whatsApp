package com.minageorge.chatdemo.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.minageorge.chatdemo.store.ChatDataBase

class HomeViewModelFactory(private val chatDataBase: ChatDataBase) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(chatDataBase) as T
    }
}