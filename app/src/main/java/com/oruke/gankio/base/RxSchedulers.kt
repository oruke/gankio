package com.oruke.gankio.base

import android.content.Context
import com.blankj.utilcode.util.NetworkUtils
import io.reactivex.FlowableTransformer
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription
import java.util.function.Consumer

class RxSchedulers {
    companion object {
        fun <T> ioMain(context: Context): ObservableTransformer<T, T> {
            return ObservableTransformer { upstream ->
                upstream.subscribeOn(Schedulers.io())
                        .doOnSubscribe {
                            Consumer<Subscription> { subscription ->
                                if (!NetworkUtils.isConnected()) {
                                    subscription.cancel();
                                }
                            }
                        }
                        .observeOn(AndroidSchedulers.mainThread())
            }
        }
    }
}