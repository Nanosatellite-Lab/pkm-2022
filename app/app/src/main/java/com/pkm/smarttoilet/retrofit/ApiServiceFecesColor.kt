package com.pkm.smarttoilet.retrofit

import com.pkm.smarttoilet.retrofit.response.ResponseFecesColor
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiServiceFecesColor {
    @Multipart
    @POST("feces_color")
    fun uploadFecesColor(
        @Part file: MultipartBody.Part
    ): Call<ResponseFecesColor>
}