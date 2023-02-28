package com.example.retrofitstudycase

import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface YaSpeechApi {

    @POST("speech/v1/stt:recognize?topic=general")
    fun recognize(
        @Query("folderId") fId: String,
        @Query("lang") lang: String = "ru-RU",
        @Body voice: RequestBody,
        @Header("Authorization") auth: String
    ) : Call<YaSpeechResponseDto>
}

