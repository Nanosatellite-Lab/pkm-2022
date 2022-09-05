package com.pkm.smarttoilet.retrofit.response

import com.google.gson.annotations.SerializedName

data class ResponseFecesColor(

	@field:SerializedName("prediction")
	val prediction: Prediction,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class Prediction(

	@field:SerializedName("hitam")
	val hitam: Double,

	@field:SerializedName("hijau")
	val hijau: Double,

	@field:SerializedName("coklat")
	val coklat: Double
)
