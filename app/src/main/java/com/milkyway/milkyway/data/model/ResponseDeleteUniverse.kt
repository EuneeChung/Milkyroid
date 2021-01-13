package com.milkyway.milkyway.data.model

data class ResponseDeleteUniverse(
    val status: Int,
    val message: String,
    val data : DeleteUniverse
)

data class DeleteUniverse(
    val universeOff: UniverseOff,
    val universeCount: Int
)

data class UniverseOff(
    val universeId: Int,
    val userId: Int,
    val cafeId:Int
)
