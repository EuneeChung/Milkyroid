package com.milkyway.milkyway.data.model


data class ResponseCafeDetail (
    val status : Int,
    val message : String,
    val data : CafeDetail
)

data class CafeDetail(
    val cafeInfo : CafeInfo,
    val menu : List<CafeMenu>
)

data class CafeInfo(
    val id : Int,
    val cafeName : String,
    val cafeAddress : String,
    val businessHours : String,
    val cafePhoneNum : String,
    val cafeLink : String,
    val universeCount : Int,
    val isUniversed : Int,
    val honeyTip : List<Int>
)

data class CafeMenu(
    val menuName : String,
    val price : String,
    val category : List<Int>
)