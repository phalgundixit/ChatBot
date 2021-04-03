package com.example.chatbot.model



import com.google.gson.annotations.SerializedName

data class Response (

    @SerializedName("errorMessage")
    val errorMessage: String,
    @SerializedName("message")
    val message: Message,
    @SerializedName("success")
    val success: Int){
}