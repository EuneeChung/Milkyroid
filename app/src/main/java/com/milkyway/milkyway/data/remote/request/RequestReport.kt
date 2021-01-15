package com.milkyway.milkyway.data.remote.request

data class RequestReport(
    val cafeName: String,
    val cafeAddress: String,
    val longitude: Double,
    val latitude: Double,
    val honeyTip: MutableList<Int>,
    val menu: MutableList<CafeReportMenu>
)

data class CafeReportMenu(
    var menuName : String,
    var price : String,
    var category : ArrayList<Int>
)
