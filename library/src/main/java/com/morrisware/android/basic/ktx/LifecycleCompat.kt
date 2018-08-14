package com.morrisware.android.basic.ktx

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import com.morrisware.android.basic.component.RxBus
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

/**
 * Created by MorrisWare on 2018/8/9.
 * Email: MorrisWare01@gmail.com
 */
fun <T> Lifecycle.registerEvent(eventType: Class<T>, consumer: Consumer<T>) {
    addObserver(object : LifecycleObserver {
        var dispose: Disposable? = null

        @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
        fun onCreate() {
            dispose = RxBus.getInstance().toDefaultFlowable(eventType, consumer)
        }

        @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        fun onDestroy() {
            dispose?.dispose()
            removeObserver(this)
        }
    })
}
