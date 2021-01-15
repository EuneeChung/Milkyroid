package com.milkyway.milkyway.data.remote.response

data class ResponseCafeSearch(
    val status: Int,
    val message: String,
    val data: List<CafeSearch>
)

data class CafeSearch(
    var cafeName: String,
    var cafeAddress: String,
    var longitude: Double,
    var latitude: Double,
    var businessHours : String
)