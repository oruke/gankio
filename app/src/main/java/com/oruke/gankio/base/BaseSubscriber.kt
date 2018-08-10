package com.oruke.gankio.base

import android.util.Log
import io.reactivex.subscribers.DisposableSubscriber


abstract class BaseSubscriber<T> : DisposableSubscriber<Result<T>>() {
    override fun onComplete() {
        Log.i("数据", "得到的得到的得到的得到")
    }

    override fun onNext(t: Result<T>) {
        val error = t.error
        if (!error) {
            Log.i("数据", "成功成功成功成功成功成功")
            success(t.results)
        } else {
            Log.i("数据", "失败失败失败失败失败失败")

        }
    }

    override fun onError(t: Throwable) {
        Log.i("数据", "错误错误错误错误错误错误错误")
        Log.i("错误异常", t.message)
//        Toast.makeText(this, "", 5)
    }

    abstract fun success(t: List<T>)
}