package com.oruke.gankio.model

data class GankIO(
    val _id: String,
    val createdAt: String,
    val desc: String,
    val publishedAt: String,
    val source: String,
    val type: String,
    val url: String,
    val used: Boolean,
    val who: String
)