package com.example.chatbot.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.chatbot.R
import com.example.chatbot.common.Constants.RECEIVE_ID
import com.example.chatbot.common.Constants.SEND_ID
import com.example.chatbot.model.Message
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.message_item.view.*
import java.lang.reflect.Type


class MessagingAdapter constructor(private var messagesList:ArrayList<Message>): RecyclerView.Adapter<MessagingAdapter.MessageViewHolder>() {
    lateinit var preferences: SharedPreferences
    inner class MessageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {



    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        return MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return messagesList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {

        preferences = holder.itemView.context.getSharedPreferences("ChatBot", Context.MODE_PRIVATE)
        preferences.edit().putString("messageList", Gson().toJson(messagesList)).apply()

        val json: String? = preferences.getString("messageList", "")
        val type: Type = object : TypeToken<List<Message?>?>() {}.type
        val messagesList: List<Message> = Gson().fromJson<List<Message>>(json, type)

        val currentMessage = messagesList[position]

        when (currentMessage.id) {
            SEND_ID -> {
                holder.itemView.tv_message.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tv_bot_message.visibility = View.GONE
            }
            RECEIVE_ID -> {
                holder.itemView.tv_bot_message.apply {
                    text = currentMessage.message
                    visibility = View.VISIBLE
                }
                holder.itemView.tv_message.visibility = View.GONE
            }
        }
    }

    fun insertMessage(message: Message) {
        this.messagesList.add(message)
        notifyItemInserted(messagesList.size)
    }

}