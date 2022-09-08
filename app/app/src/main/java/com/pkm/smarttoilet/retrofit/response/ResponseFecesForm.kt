package com.pkm.smarttoilet.retrofit.response

import com.google.gson.annotations.SerializedName

data class ResponseFecesForm(

	@field:SerializedName("prediction")
	val prediction: Prediction2,

	@field:SerializedName("message")
	val message: String,

	@field:SerializedName("status")
	val status: Boolean
)

data class Prediction2(

	@field:SerializedName("tipe_3")
	val tipe3: Double,

	@field:SerializedName("tipe_4")
	val tipe4: Double,

	@field:SerializedName("tipe_1")
	val tipe1: Double,

	@field:SerializedName("tipe_2")
	val tipe2: Double,

	@field:SerializedName("tipe_5")
	val tipe5: Double,

	@field:SerializedName("tipe_6")
	val tipe6: Double
)
