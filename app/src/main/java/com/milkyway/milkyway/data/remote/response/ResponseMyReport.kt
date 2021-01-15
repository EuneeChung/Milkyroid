package com.milkyway.milkyway.data.remote.response

data class ResponseMyReport(
    val status : String,
    val message : String,
    val data : MyReport
)

data class MyReport(
    val cancel : List<CancelReport>,
    val ing : List<IngReport>,
    val done : List<DoneReport>
)

data class CancelReport(
    val id : Int,
    val cafeName : String,
//    val cafeAddress : String,
    val created_at : String,
    val rejectReasonId : Int
)

data class IngReport(
    val id : Int,
    val cafeName : String,
//    val cafeAddress : String,
    val created_at : String
)

data class DoneReport(
    val id : Int,
    val cafeName : String,
    val cafeAddress : String,
    val created_at : String,
    val category : List<Int>
)

