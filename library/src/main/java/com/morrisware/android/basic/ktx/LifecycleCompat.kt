package com.mumuwu.paradise.ktx

import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.LifecycleObserver
import android.arch.lifecycle.OnLifecycleEvent
import android.support.v4.util.ArrayMap
import com.morrisware.android.basic.component.RxBus
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Consumer

/**
 * Created by MorrisWare on 2018/8/9.
 * Email: MorrisWare01@gmail.com
 */
object EventLifecycleHelper {
    val map = ArrayMap<Consumer<out Any>, EventObserverWrapper<out Any>>()
}

class EventObserverWrapper<T>(
    val lifecycle: Lifecycle,
    private val eventType: Class<T>,
    private val consumer: Consumer<T>
) : LifecycleObserver {

    val compositeDisposable = CompositeDisposable()

    fun isAttachedTo(lifecycle: Lifecycle): Boolean {
        return this.lifecycle === lifecycle
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun onCreate() {
        compositeDisposable.add(RxBus.getInstance().toDefaultFlowable(eventType, consumer))
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun onDestroy() {
        compositeDisposable.clear()
        EventLifecycleHelper.map.remove(consumer)
    }
}

fun <T : Any> Lifecycle.registerEvent(eventType: Class<T>, consumer: Consumer<T>) {
    if (currentState == Lifecycle.State.DESTROYED) {
        // ignore
        return
    }
    val wrapper = EventLifecycleHelper.map[consumer]
    if (wrapper != null) {
        if (!wrapper.compositeDisposable.isDisposed) {
            if (!wrapper.isAttachedTo(this)) {
                throw IllegalArgumentException("Cannot add the same Event Consumer with different lifecycles")
            }
            return
        }
        EventLifecycleHelper.map.remove(consumer)
    }

    val newWrapper = EventObserverWrapper(this, eventType, consumer)
    EventLifecycleHelper.map[consumer] = newWrapper
    this.addObserver(newWrapper)
}
