package com.pkm.smarttoilet.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pkm.smarttoilet.retrofit.ApiConfigUrineColor
import com.pkm.smarttoilet.retrofit.response.ResponseFecesColor
import com.pkm.smarttoilet.retrofit.response.ResponseUrineColor
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UrineResultViewModel: ViewModel() {

    private val _urineColorData = MutableLiveData<ResponseUrineColor>()
    val urineColorData:LiveData<ResponseUrineColor> = _urineColorData

    fun upUrineColor(file: MultipartBody.Part){
        val client = ApiConfigUrineColor.getApiServiceUrineColor().uploadUrineColor(file)
        client.enqueue(object : Callback<ResponseUrineColor>{
            override fun onResponse(
                call: Call<ResponseUrineColor>,
                response: Response<ResponseUrineColor>
            ) {
                if (response.isSuccessful){
//                    _isLoading.value = false
                    val responseBody = response.body()
                    _urineColorData.value = response.body()

                    Log.d(TAG, "onResponse: ${response.body()}")
                }
                else {
//                    _isLoading.value = false
                    Log.e(TAG, "onFailure: ${response.message()}")
                    Log.e(TAG, "onFailure: ${response.code()}")
                    Log.e(TAG, "onFailure: ${response.errorBody()}")
//                    _responseCode.value = -1
                }

            }

            override fun onFailure(call: Call<ResponseUrineColor>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object {
        private const val TAG = "UrineResultViewModel"
    }
}