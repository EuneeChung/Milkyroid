package com.milkyway.milkyway.data.model

data class CafeSearchResponse(
    val status: Int,
    val message: String,
    val data: ArrayList<CafeSearch>
)