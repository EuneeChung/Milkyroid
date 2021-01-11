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
    val universeCount : Int,
    val cafeMapX: Double,
    val cafeMapY: Double,
    val cafeAddress: String,
    val businessHours: String,
    val isUniversed: Boolean
)