package com.pkm.smarttoilet.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pkm.smarttoilet.retrofit.ApiConfigFecesColor
import com.pkm.smarttoilet.retrofit.ApiConfigFecesForm
import com.pkm.smarttoilet.retrofit.response.ResponseFecesColor
import com.pkm.smarttoilet.retrofit.response.ResponseFecesForm
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FecesResultViewModel: ViewModel() {

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    private val _myData = MutableLiveData<String>()
    val myData: LiveData<String> = _myData

    fun addText(updatedNum: String){
        _myData.value = updatedNum
        Log.d(TAG, "addText: ${myData.value} ${_myData.value}")
    }

    private val _fecesColorData = MutableLiveData<ResponseFecesColor>()
    val fecesColorData: LiveData<ResponseFecesColor> = _fecesColorData

    private val _fecesFormData = MutableLiveData<ResponseFecesForm>()
    val feceFormData:LiveData<ResponseFecesForm> = _fecesFormData

    fun upFecesColor(file: MultipartBody.Part){
        _isLoading.value = true
        val client = ApiConfigFecesColor.getApiServiceFecesColor().uploadFecesColor(file)
        client.enqueue(object : Callback<ResponseFecesColor>{
            override fun onResponse(
                call: Call<ResponseFecesColor>,
                response: Response<ResponseFecesColor>
            ) {
                if (response.isSuccessful){
                    _isLoading.value = false
                    val responseBody = response.body()
                    _fecesColorData.value = response.body()

                    Log.d(TAG, "onResponse: ${response.body()}")
                }
                else {
                    _isLoading.value = false
                    Log.e(TAG, "onFailure: ${response.message()}")
                    Log.e(TAG, "onFailure: ${response.code()}")
                    Log.e(TAG, "onFailure: ${response.errorBody()}")
//                    _responseCode.value = -1
                }
            }

            override fun onFailure(call: Call<ResponseFecesColor>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }
    fun upFecesForm(file: MultipartBody.Part){
        val client = ApiConfigFecesForm.getApiServiceFecesForm().uploadFecesForm(file)
        client.enqueue(object: Callback<ResponseFecesForm>{
            override fun onResponse(
                call: Call<ResponseFecesForm>,
                response: Response<ResponseFecesForm>
            ) {
                if (response.isSuccessful){
//                    _isLoading.value = false
                    val responseBody = response.body()
                    _fecesFormData.value = response.body()

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

            override fun onFailure(call: Call<ResponseFecesForm>, t: Throwable) {
//                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }

        })
    }

    companion object {
        private const val TAG = "FecesResultViewModel"
    }
}