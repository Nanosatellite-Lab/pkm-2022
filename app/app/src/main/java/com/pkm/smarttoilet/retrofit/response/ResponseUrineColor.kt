package com.pkm.smarttoilet.retrofit.response

import com.google.gson.annotations.SerializedName

data class ResponseUrineColor(

	@field:SerializedName("prediction")
	val prediction: Prediction3,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class Prediction3(

	@field:SerializedName("kuning")
	val kuning: Double,

	@field:SerializedName("hijau")
	val hijau: Double,

	@field:SerializedName("bening")
	val bening: Double
)
