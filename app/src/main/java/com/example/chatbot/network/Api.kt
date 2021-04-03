package com.example.chatbot.network


import com.example.chatbot.model.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.QueryMap

interface Api {
    @Headers(value=["Content-type: application/json"])
    @GET("chat")
   suspend fun getBotMessage(
        @QueryMap parameters :HashMap<String, String> ): Response


}