package com.minageorge.chatdemo.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.minageorge.chatdemo.store.ChatDataBase
import com.minageorge.chatdemo.store.models.rooms.RoomsEntity
import com.minageorge.chatdemo.store.models.subscribers.SubscribersEntity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import java.util.*

class HomeViewModel(private val chatDataBase: ChatDataBase) : ViewModel() {

    val insertRoomObservable = BehaviorSubject.create<RoomsEntity>()
    val insertAllRoomObservable = BehaviorSubject.create<List<RoomsEntity>>()
    val insertSubscriberObservable = BehaviorSubject.create<SubscribersEntity>()
    val roomsLiveData = MutableLiveData<List<RoomsEntity>>()
    val disposable = CompositeDisposable()

    init {

        // get rooms
        disposable.add(
            chatDataBase.getRoomsDao().getRooms()
                .toObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(roomsLiveData::setValue)
        )

        // insert room
        disposable.add(insertRoomObservable.observeOn(Schedulers.io())
            .subscribe { chatDataBase.getRoomsDao().upsert(it) })
        // insert rooms
        disposable.add(insertAllRoomObservable.observeOn(Schedulers.io())
            .subscribe { chatDataBase.getRoomsDao().upsertAll(it) })

        // insert subscribers
        disposable.add(insertSubscriberObservable.observeOn(Schedulers.io())
            .subscribe { chatDataBase.getSubscribersDao().upsert(it) })

        // 1555191945571


    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}