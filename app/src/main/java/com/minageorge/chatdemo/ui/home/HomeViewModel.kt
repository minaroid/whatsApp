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




        for (x in 4..50000) {
            insertRoomObservable.onNext(RoomsEntity(
                        System.currentTimeMillis(),
                        "Room-$x",
                        "ffewfewfqfqw",
                        "gfefewfewfbfb",
                        Date(),
                        5
                    ))
//            val list = ArrayList<RoomsEntity>()
//            for (y in 1..500) {
//                list.add(
//                    RoomsEntity(
//                        System.currentTimeMillis(),
//                        "Room-$x",
//                        "ffewfewfqfqw",
//                        "gfefewfewfbfb",
//                        Date(),
//                        5
//                    )
//                )
//            }
//            insertAllRoomObservable.onNext(list)
        }
    }

    override fun onCleared() {
        disposable.clear()
        super.onCleared()
    }
}