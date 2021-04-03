package com.example.chatbot.network

import com.google.gson.annotations.SerializedName

data class BotRequest(

    @SerializedName("apiKey")
    var apiKey: String? = "6nt5d1nJHkqbkphe",

    @SerializedName("chatBotID")
    var chatBotID: String? = "63906",

    @SerializedName("externalID")
    var externalID: String? = "",

    @SerializedName("message")
    var message: String? = ""

    ) {

}