package com.example.chatbot.model



import com.google.gson.annotations.SerializedName


data class Message(

    @SerializedName("chatBotID")
    val chatBotID: Int,
    @SerializedName("chatBotName")
    val chatBotName: String,
    @SerializedName("emotion")
    val emotion: Any,
    @SerializedName("message")
    val message: String,

    val id: String

)