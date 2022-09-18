package com.pkm.smarttoilet.retrofit

import com.pkm.smarttoilet.retrofit.response.ResponseFecesForm
import com.pkm.smarttoilet.retrofit.response.ResponseUrineColor
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiServiceUrineColor {
    @Multipart
    @POST("urine_warna")
    fun uploadUrineColor(
        @Part file: MultipartBody.Part
    ): Call<ResponseUrineColor>

}