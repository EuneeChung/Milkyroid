package com.milkyway.milkyway.data.model

data class ResponseUniverse(
    val status: Int,
    val message: String,
    val data : UniverseCafe
)

data class UniverseCafe(
    val aroundUniverse: List<AroundUniverse>,
    val nickName: String
)

data class AroundUniverse(
    val id: Int,
    val cafeName: String,
    val cafeAddress: String,
    val businessHours: String,
    val longitude: Double,
    val latitude: Double
)