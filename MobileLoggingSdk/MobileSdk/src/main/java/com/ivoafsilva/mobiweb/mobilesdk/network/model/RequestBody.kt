package com.ivoafsilva.mobiweb.mobilesdk.network.model

import com.google.gson.annotations.SerializedName

data class RequestBody(
    @SerializedName("myString")
    val myString: String
)