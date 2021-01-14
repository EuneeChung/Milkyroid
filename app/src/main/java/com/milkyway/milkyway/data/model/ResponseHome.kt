package com.milkyway.milkyway.data.model

data class ResponseHome(
    val status: Int,
    val message: String,
    val data : HomeCafe
)

data class HomeCafe(
    val result: List<AroundCafe>,
    val nickName: String
)

data class AroundCafe(
    val id: Int,
    val cafeName: String,
    var universeCount : Int,
    val longitude: Double,
    val latitude: Double,
    val cafeAddress: String,
    val businessHours: String?,
    var isUniversed: Boolean
)