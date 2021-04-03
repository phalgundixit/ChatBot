package com.example.chatbot.di

import com.example.chatbot.repository.MessageRepository
import com.example.chatbot.network.ApiImp

import com.example.chatbot.network.Api
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(ApplicationComponent::class)
class AppModule {



    @Provides
    fun providesMessageRepository(apiImp: ApiImp): MessageRepository =
        MessageRepository(apiImp)

    @Provides
    @Singleton
    fun getBaseUrl():String = "https://www.personalityforge.com/api/"

    @Provides
    @Singleton
    fun getRetrofitBuilder(baseUrl:String):Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    @Singleton
    fun getApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

}