package com.pkm.smarttoilet.retrofit.response

import com.google.gson.annotations.SerializedName

data class ResponseLatestImageUrl(

	@field:SerializedName("imgPath")
	val imgPath: String,

	@field:SerializedName("status")
	val status: Boolean
)
