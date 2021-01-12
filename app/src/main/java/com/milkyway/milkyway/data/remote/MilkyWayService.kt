package com.milkyway.milkyway.data.remote

import com.milkyway.milkyway.data.model.*
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

    // ModifyActivity - Delete
    @POST("report/{cafeId}/deleteCafe")
    suspend fun delete(
        @Header("token") token : String,
        @Body body :DeleteModify,
        @Path("cafeId") cafeId:Int
    ) : ResponseModify

    // RequestModificationsActivity
    @POST("report/{cafeId}/editCafe")
    suspend fun modify(
        @Header("token") token : String,
        @Body body :RequestModify,
        @Path("cafeId") cafeId:Int
    ):ResponseModify

}