package com.oruke.gankio.service

import com.oruke.gankio.base.Result
import com.oruke.gankio.model.GankIO
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiService {

    @GET("api/data/{type}/{size}/{page}")
    fun getData(@Path("type") type: String, @Path("size")size: Int, @Path("page")page: Int) : Flowable<Result<GankIO>>
}