package com.younesbelouche.rickandmorty.data.data_sources.remote.dto

data class PageInfoDto(
    val count: Long,
    val pages: Long,
    val next: String,
    val prev: Any?,
)
