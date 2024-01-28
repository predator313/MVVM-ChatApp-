package com.aamirashraf.mvvmchatappusingstreamsdk

import android.app.Application
import dagger.Module
import dagger.hilt.android.HiltAndroidApp
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.livedata.ChatDomain
import javax.inject.Inject

@HiltAndroidApp
class ChatApplication:Application() {
    //initialize stream sdk here
    @Inject
    lateinit var client: ChatClient
    override fun onCreate() {
        super.onCreate()
        ChatDomain.Builder(
            client,
            applicationContext
        ).build()
    }
}