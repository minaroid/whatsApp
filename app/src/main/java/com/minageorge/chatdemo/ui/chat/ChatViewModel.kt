package com.minageorge.chatdemo.ui.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.minageorge.chatdemo.store.ChatDataBase
import com.minageorge.chatdemo.store.models.messages.MessagesEntity
import com.minageorge.chatdemo.store.models.subscribers.SubscribersEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.*

class ChatViewModel(private val chatDataBase: ChatDataBase) : ViewModel() {

    val subscribersLiveData = MutableLiveData<List<SubscribersEntity>>()
    val messagesLiveData = MutableLiveData<List<MessagesEntity>>()

    private val insertMessagebservable = BehaviorSubject.create<MessagesEntity>()
    private var roomId: Long = 0
    private val disposable = CompositeDisposable()

    init {
        disposable.add(insertMessagebservable.observeOn(Schedulers.io())
            .subscribe { chatDataBase.getMessagesDao().upsert(it) })
    }

    fun getRoomSubscribersAndMsgs(roomId: Long) {
        this.roomId = roomId

        disposable.add(
            chatDataBase.getSubscribersDao().getSubscribers(roomId)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscribersLiveData::setValue)
        )

        disposable.add(
            chatDataBase.getMessagesDao().getMessages(roomId)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(messagesLiveData::setValue)
        )
    }

    fun insertMessage(message: String) {
        insertMessagebservable
            .onNext(MessagesEntity(System.currentTimeMillis(), roomId, 1234, message, Date()))
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }

}