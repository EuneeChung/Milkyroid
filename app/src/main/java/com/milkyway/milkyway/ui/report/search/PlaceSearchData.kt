package com.milkyway.milkyway.ui.report.search

//리스트에 출력할 data class
data class PlaceSearchData (
    var cafeName: String,
    var cafeAddress: String,
    var longitude: String,
    var latitude: String,
    var isReported: Boolean
)