package com.milkyway.milkyway.data.model

data class ResponseHome(
    val status: Int,
    val message: String,
    val data : HomeCafe
)

data class HomeCafe(
    val aroundCafe: List<AroundCafe>,
    val nickName: String
)

data class AroundCafe(
    val id: Int,
    val cafeName: String,
    val cafeAddress: String,
    val businessHours: String,
    val cafeMapX: Double,
    val cafeMapY: Double,
    val isReal: Boolean
)