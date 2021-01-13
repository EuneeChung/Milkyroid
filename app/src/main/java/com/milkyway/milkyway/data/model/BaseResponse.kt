package com.milkyway.milkyway.data.model

/**
 * Created By Crystal
 * on 1월 12, 2021
 */

data class BaseResponse<T>(
    val status: Int,
    val success: Boolean,
    val message: String,
    val data: T
)
