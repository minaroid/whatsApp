package com.minageorge.chatdemo

import android.app.Application
import com.minageorge.chatdemo.store.ChatDataBase
import com.minageorge.chatdemo.ui.chat.ChatViewModelFactory
import com.minageorge.chatdemo.ui.home.HomeViewModelFactory
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton

class ChatApplication : Application(), KodeinAware {
    override val kodein = Kodein.lazy {
        import(androidXModule(this@ChatApplication))
        bind() from singleton { ChatDataBase(instance()) }
        bind() from provider { HomeViewModelFactory(instance()) }
        bind() from provider { ChatViewModelFactory(instance()) }
    }
}