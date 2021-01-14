package com.milkyway.milkyway.data.model

//리스트에 출력할 data class
data class PlaceSearchData (
    var cafeName: String,
    var cafeAddress: String,
    var longitude: Double,
    var latitude: Double,
    var isReported: Boolean
)