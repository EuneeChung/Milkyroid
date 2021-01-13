package com.milkyway.milkyway.data.remote

import com.milkyway.milkyway.data.model.*
import retrofit2.Call
import retrofit2.http.*


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

    //ReportPlaceSearch
    @GET("/search/report/cafe")
    fun requestPlaceSearch(
        @Header("token") token: String,
        @Query("query") query: String
    ): Call<PlaceSearchResponse>

    //HomeCafeSearch
    @GET("/search/{toString}")
    fun requestCafeSearch(
        @Header("token") token: String,
        @Path("toString") toString: String
    ): Call<CafeSearchResponse>
}