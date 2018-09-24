package com.oruke.gankio.base

import com.google.gson.annotations.SerializedName

data class Result<T>(
        var error: Boolean = false,
        var results: ArrayList<T> = ArrayList()
) {
}