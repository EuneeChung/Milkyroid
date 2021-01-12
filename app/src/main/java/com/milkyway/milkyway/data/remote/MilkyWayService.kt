package com.milkyway.milkyway.data.remote

import com.milkyway.milkyway.data.model.*
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST


interface MilkyWayService {
    // SignUp
    @POST("users/signup")
    suspend fun signUp(
        @Body body : RequestSign
    ) : ResponseToken

    // SignIn
    @POST("users/signin")
    suspend fun signIn(
        @Body body : RequestSign
    ) : ResponseToken

    // HomeFragment
    @GET("home/milkyHome")
    suspend fun home(
        @Header("token") token : String
    ) : ResponseHome

    // UniverseFragment
    @GET("universe/universeHome")
    suspend fun universe(
        @Header("token") token : String
    ) : ResponseUniverse

    // CafeDetailFragment
//    @GET("cafe/{cafeId}")
    @GET("cafe/17")
    suspend fun cafeDetail(
        @Header("token") token : String
    ) : ResponseCafeDetail
}