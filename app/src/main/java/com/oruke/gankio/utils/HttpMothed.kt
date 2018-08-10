package com.oruke.gankio.utils

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class HttpMothed {

    companion object {
        val BASE_URL = "http://gank.io/"
        val TIME_OUT = 5L

        fun retrofit(): Retrofit {
            val client = OkHttpClient()
            client.newBuilder().connectTimeout(TIME_OUT, TimeUnit.SECONDS)
            return Retrofit.Builder().baseUrl(BASE_URL)
                    .client(client)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
        }
    }
}
