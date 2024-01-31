package com.aamirashraf.mvvmchatappusingstreamsdk

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.android.HiltAndroidApp
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.livedata.ChatDomain
import java.lang.reflect.Constructor
import javax.inject.Inject
import javax.inject.Singleton

@HiltAndroidApp
class ChatApplication : Application() {

    @Inject
    lateinit var client: ChatClient

    override fun onCreate() {
        super.onCreate()
        ChatDomain.Builder(client, applicationContext).build()
//        super.onCreate()
    }
}