package com.milkyway.milkyway.data.remote.response

import com.milkyway.milkyway.data.model.CafeSearch

data class ResponseCafeSearch(
    val status: Int,
    val message: String,
    val data: ArrayList<CafeSearch>
)