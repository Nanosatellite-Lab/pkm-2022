package com.pkm.smarttoilet.retrofit

import com.pkm.smarttoilet.retrofit.response.ResponseLatestImageUrl
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiServiceLatestImageUrl {
    @GET("upload")
    fun getLatestImageUrl(): Call<ResponseLatestImageUrl>
}