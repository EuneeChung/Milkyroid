package com.milkyway.milkyway.ui.report.search

//리스트에 출력할 data class
data class PlaceSearchData (
    val placeName: String,
    val placeLocation: String,
    val alreadyIn: Boolean
)