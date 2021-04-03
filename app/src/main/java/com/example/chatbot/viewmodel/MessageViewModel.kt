package com.example.chatbot.viewmodel

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.chatbot.repository.MessageRepository
import com.example.chatbot.model.Response
import kotlinx.coroutines.launch

class MessageViewModel @ViewModelInject constructor(private val messageRepository: MessageRepository): ViewModel(){
    val botResponse: MutableLiveData<Response> = MutableLiveData()
    fun getBotMessage(params:HashMap<String,String>)
    {
        viewModelScope.launch {
            botResponse.value = messageRepository.getBotMessage(params)

        }

    }
}