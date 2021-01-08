package com.milkyway.milkyway.data.remote

import com.milkyway.milkyway.data.model.RequestSign
import com.milkyway.milkyway.data.model.ResponseToken
import retrofit2.http.Body
import retrofit2.http.POST

interface MilkyWayService {
    // SignUp
    @POST("users/signup")
    suspend fun signUp(
        @Body body : RequestSign
    ) : ResponseToken

}