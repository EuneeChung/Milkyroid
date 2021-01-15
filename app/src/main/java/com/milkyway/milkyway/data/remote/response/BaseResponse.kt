package com.milkyway.milkyway.data.remote.response

/**
 * Created By Crystal
 * on 1월 14, 2021
 */

data class BaseResponse<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T
)