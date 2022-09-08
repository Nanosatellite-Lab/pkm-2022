package com.pkm.smarttoilet.retrofit

import com.pkm.smarttoilet.retrofit.response.ResponseFecesColor
import com.pkm.smarttoilet.retrofit.response.ResponseFecesForm
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface ApiServiceFecesForm {
        @Multipart
        @POST("feces_form")
        fun uploadFecesForm(
            @Part file: MultipartBody.Part
        ): Call<ResponseFecesForm>

}