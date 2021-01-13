package com.milkyway.milkyway.data.model


data class PlaceSearchResponse(
    val status: Int,
    val message: String,
    val data: ArrayList<PlaceSearch>
)

