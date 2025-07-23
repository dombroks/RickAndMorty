package com.younesbelouche.rickandmorty.domain.entities

data class PageInfo(
    val count: Long,
    val pages: Long,
    val next: String,
    val prev: Any?,
)
