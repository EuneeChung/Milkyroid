package com.milkyway.milkyway.data.remote

import com.milkyway.milkyway.data.remote.request.*
import com.milkyway.milkyway.data.remote.response.*
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

    // HomeFragment Category Select
    @GET("home/{categoryId}")
    suspend fun homeCategory(
        @Header("token") token : String,
        @Path("categoryId") categoryId : Int
    ) : ResponseHome

    @POST("universe/universeOn")
    fun addMyUniverseHome(
        @Header("token") token : String,
        @Body body : RequestCafeId
    ) : Call<ResponseAddUniverse>

    @DELETE("universe/{cafeId}")
    fun deleteUniverseMarker(
        @Header("token") token : String,
        @Path("cafeId") cafeId : Int
    ) : Call<ResponseDeleteUniverse>

    // UniverseFragment
    @GET("universe/universeHome")
    suspend fun universe(
        @Header("token") token : String
    ) : ResponseUniverse

    // CafeDetailActivity
    @GET("cafe/{cafeId}")
    suspend fun cafeDetail(
        @Header("token") token : String,
        @Path("cafeId") cafeId: Int
    ) : ResponseCafeDetail

    // ModifyActivity - Delete
    @POST("report/{cafeId}/deleteCafe")
    suspend fun delete(
        @Header("token") token : String,
        @Body body : DeleteModify,
        @Path("cafeId") cafeId:Int
    ) : ResponseModify

    // RequestModificationsActivity
    @POST("report/{cafeId}/editCafe")
    suspend fun modify(
        @Header("token") token : String,
        @Body body : RequestModify,
        @Path("cafeId") cafeId:Int
    ): ResponseModify

    //ReportPlaceSearch
    @GET("search/report/cafe")
    fun requestPlaceSearch(
        @Header("token") token: String,
        @Query("query") query: String
    ): Call<ResponsePlaceSearch>

    //HomeCafeSearch
    @GET("search/{toString}")
    fun requestCafeSearch(
        @Header("token") token: String,
        @Path("toString") toString: String
    ): Call<ResponseCafeSearch>

    // MyReportFragment
    @GET("report")
    suspend fun myReport(
        @Header("token") token : String
    ) : ResponseMyReport

    //universe delete - UniverseBottomSheet
    @DELETE("universe/{cafeId}")
    suspend fun deleteUniverse(
        @Header("token") token: String,
        @Path("cafeId") cafeId: Int
    ): ResponseDeleteUniverse

    // myreport cancel delete
    @DELETE("report/{cafeId}")
    suspend fun deleteCancel(
        @Header("token") token: String,
        @Path("cafeId") cafeId: Int
    ): ResponseDeleteCancel


    // universe add - CafeDetail
    @POST("universe/universeOn")
    suspend fun addUniverseDetail(
        @Header("token") token : String,
        @Body body : RequestCafeId
    ) : ResponseAddUniverse

    //Cafe Report
    @POST("report")
    fun requestCafeReport(
        @Header("token") token : String,
        @Body body : RequestReport
    ) : Call<BaseResponse<Unit>>
}