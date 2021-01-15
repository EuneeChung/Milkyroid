package com.milkyway.milkyway.data.remote.response


data class ResponsePlaceSearch(
    val status: Int,
    val message: String,
    val data: ArrayList<PlaceSearch>
)

