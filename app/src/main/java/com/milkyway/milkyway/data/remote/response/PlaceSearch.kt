package com.milkyway.milkyway.data.remote.response

data class PlaceSearch(
    val cafeName: String,
    val cafeAddress: String,
    val longitude: Double,
    val latitude: Double,
    val isReported: Boolean
)
