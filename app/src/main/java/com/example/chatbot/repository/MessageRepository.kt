package com.example.chatbot.repository


import com.example.chatbot.model.Response
import com.example.chatbot.network.ApiImp
import javax.inject.Inject

class MessageRepository @Inject constructor(private val apiImp: ApiImp) {

   suspend fun getBotMessage(params:HashMap<String, String>): Response = apiImp.getBotMessage(params)

}
