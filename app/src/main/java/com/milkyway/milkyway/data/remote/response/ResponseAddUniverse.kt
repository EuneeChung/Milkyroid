package com.milkyway.milkyway.data.remote.response

data class ResponseAddUniverse(
    val status: Int,
    val message: String,
    val data: AddUniverseData
)

data class AddUniverseData(
    val universeOn: UniverseOn,
    val universeCount: Int
)

data class UniverseOn(
    val cafeId: Int,
    val universeId: Int,
    val userId: Int
)