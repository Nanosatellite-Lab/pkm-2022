package com.pkm.smarttoilet.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pkm.smarttoilet.retrofit.ApiConfigFecesColor
import com.pkm.smarttoilet.retrofit.ApiConfigLatestImageUrl
import com.pkm.smarttoilet.retrofit.response.ResponseFecesColor
import com.pkm.smarttoilet.retrofit.response.ResponseLatestImageUrl
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel: ViewModel() {

    private val _latestImage = MutableLiveData<ResponseLatestImageUrl>()
    val latestImageUrl: LiveData<ResponseLatestImageUrl> = _latestImage

    fun getLatestImageUrl(){

        val client = ApiConfigLatestImageUrl.getApiServiceLatestImageUrl().getLatestImageUrl()
        client.enqueue(object : Callback<ResponseLatestImageUrl>{
            override fun onResponse(
                call: Call<ResponseLatestImageUrl>,
                response: Response<ResponseLatestImageUrl>
            ) {
                if (response.isSuccessful){
                    _latestImage.value = response.body()
                    Log.d(TAG, "onResponse: ${response.body()}")
                }
                else {
                    Log.e(TAG, "onFailure: ${response.message()}")
                    Log.e(TAG, "onFailure: ${response.code()}")
                    Log.e(TAG, "onFailure: ${response.errorBody()}")
                }
            }

            override fun onFailure(call: Call<ResponseLatestImageUrl>, t: Throwable) {
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object {
        private const val TAG = "MainViewModel"
    }

}