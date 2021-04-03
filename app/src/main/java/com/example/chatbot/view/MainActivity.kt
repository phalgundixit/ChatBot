package com.example.chatbot.view

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chatbot.R
import com.example.chatbot.adapter.MessagingAdapter
import com.example.chatbot.common.Constants.RECEIVE_ID
import com.example.chatbot.common.Constants.SEND_ID
import com.example.chatbot.model.Message
import com.example.chatbot.viewmodel.MessageViewModel
import com.example.chatbot.network.BotRequest
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.lang.reflect.Type
import java.util.HashMap

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private val messageViewModel: MessageViewModel by viewModels()
    private lateinit var adapter: MessagingAdapter
    private lateinit var preferences: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        preferences = this.getSharedPreferences("ChatBot", Context.MODE_PRIVATE)
        val json: String? = preferences.getString("messageList", "")
        val type: Type = object : TypeToken<ArrayList<Message?>?>() {}.type
        if(json==""){
            recyclerView(arrayListOf<Message>())
        }else{
            val messagesList: ArrayList<Message> = Gson().fromJson<ArrayList<Message>>(json, type)
            recyclerView(messagesList)
        }




        clickEvents()

        messageViewModel.botResponse.observe(this, Observer { response ->

            adapter.insertMessage(Message(63906,"","",response.message.message, RECEIVE_ID))

            rv_messages.scrollToPosition(adapter.itemCount - 1)

        })

    }
    private fun recyclerView(messageList:ArrayList<Message>) {
        adapter = MessagingAdapter(messageList)
        rv_messages.adapter = adapter
        rv_messages.layoutManager = LinearLayoutManager(applicationContext)

    }
    private fun clickEvents() {

        btn_send.setOnClickListener {

            sendMessage()
        }

        et_message.setOnClickListener {
            GlobalScope.launch {

                withContext(Dispatchers.Main) {
                    rv_messages.scrollToPosition(adapter.itemCount - 1)

                }
            }
        }
    }

    private fun sendMessage() {
        val message = et_message.text.toString()

        if (message.isNotEmpty()) {
            et_message.setText("")
            adapter.insertMessage(Message(63906,"","",message, SEND_ID))
            rv_messages.scrollToPosition(adapter.itemCount - 1)
            val ob =  BotRequest("6nt5d1nJHkqbkphe","63906","Batman",message)

            var hashMap : HashMap<String, String>
                    = HashMap<String, String> ()
            hashMap.put("apiKey", ob.apiKey.toString())
            hashMap.put("chatBotID",ob.chatBotID.toString())
            hashMap.put("externalID", ob.externalID.toString())
            hashMap.put("message", ob.message.toString())

            messageViewModel.getBotMessage(hashMap)


        }
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == R.id.bot1){
            Toast.makeText(this, "bot 1 Chatting", Toast.LENGTH_SHORT).show()

            return true
        }else if (id == R.id.bot2){
            Toast.makeText(this,"bot 2 Chatting", Toast.LENGTH_SHORT).show()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
    override fun onStart() {
        super.onStart()
        GlobalScope.launch {
            delay(100)
            withContext(Dispatchers.Main) {
                rv_messages.scrollToPosition(adapter.itemCount - 1)
            }
        }
    }
}