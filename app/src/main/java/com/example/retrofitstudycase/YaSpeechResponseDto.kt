package com.example.retrofitstudycase

import com.google.gson.annotations.SerializedName

data class YaSpeechResponseDto(
    @SerializedName("result")
    val result: String?,

    @SerializedName("error_code")
    val errorCode: String?,
    @SerializedName("error_message")
    val errorMessage: String?

    )
