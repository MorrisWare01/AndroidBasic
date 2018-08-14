package com.morrisware.android.basic.ktx

import android.arch.lifecycle.LiveData
import com.morrisware.android.basic.bean.Resource
import com.morrisware.android.basic.bean.Status
import io.reactivex.Flowable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.subscribers.ResourceSubscriber
import java.util.concurrent.atomic.AtomicBoolean

/**
 * Created by MorrisWare on 2018/7/13.
 * Email: MorrisWare01@gmail.com
 */
fun <T> Flowable<T>.asLiveData(doOnNext: ((T) -> Resource<T>)? = null): LiveData<Resource<T>> {
    return object : LiveData<Resource<T>>() {
        private var started = AtomicBoolean(false)
        private var disposable: Disposable? = null

        override fun onActive() {
            super.onActive()
            if (started.compareAndSet(false, true)) {
                disposable = this@asLiveData
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : ResourceSubscriber<T>() {
                        override fun onNext(t: T) {
                            try {
                                value = doOnNext?.invoke(t) ?: Resource(Status.SUCCESS, t)
                            } catch (e: Throwable) {
                                onError(e)
                            }
                        }

                        override fun onError(t: Throwable?) {
                            value = Resource(Status.ERROR, throwable = t)
                        }

                        override fun onComplete() {
                        }
                    })
            }
        }

        override fun onInactive() {
            super.onInactive()
            disposable?.apply {
                if (this.isDisposed) {
                    this.dispose()
                }
            }
        }
    }
}