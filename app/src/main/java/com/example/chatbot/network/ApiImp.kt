package com.example.chatbot.network

import com.example.chatbot.model.Response
import javax.inject.Inject

class ApiImp  @Inject constructor(private val api: Api) {
    suspend fun getBotMessage(params:HashMap<String, String>):Response = api.getBotMessage(params)
}

