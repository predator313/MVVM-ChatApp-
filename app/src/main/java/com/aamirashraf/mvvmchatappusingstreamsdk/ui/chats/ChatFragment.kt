package com.aamirashraf.mvvmchatappusingstreamsdk.ui.chats

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.viewbinding.ViewBinding
import com.aamirashraf.mvvmchatappusingstreamsdk.databinding.FragmentChatBinding
import com.aamirashraf.mvvmchatappusingstreamsdk.ui.BindingFragment

class ChatFragment:BindingFragment<FragmentChatBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentChatBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}