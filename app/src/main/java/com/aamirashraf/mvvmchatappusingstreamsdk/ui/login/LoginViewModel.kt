package com.aamirashraf.mvvmchatappusingstreamsdk.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.aamirashraf.mvvmchatappusingstreamsdk.MIN_USERNAME_LEN
import dagger.hilt.android.lifecycle.HiltViewModel
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.call.await
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val client: ChatClient
):ViewModel() {
    private val _loginEvent= MutableSharedFlow<LoginEvents>()
    val loginEvent=_loginEvent.asSharedFlow()
    private fun isValidUserName(userName:String):Boolean{
        return userName.length>= MIN_USERNAME_LEN
    }
    fun connectUser(userName: String){
        val trimUserName=userName.trim()
        viewModelScope.launch {
            if(isValidUserName(trimUserName)){
                //now lets make the network call in the stream
                val res=client.connectGuestUser(
                   userId =  trimUserName,
                   username =  trimUserName
                ).await()
                if(res.isError){
                    //means some error from stream backend
                    _loginEvent.emit(LoginEvents.ErrorLogin(res.error().message?:"Unknown Error"))
                    return@launch
                }
                _loginEvent.emit(LoginEvents.Success)
            }
            else{
                _loginEvent.emit(LoginEvents.ErrorInputTooShort)
            }
        }
    }

     sealed class LoginEvents{
         data object ErrorInputTooShort:LoginEvents()
         data class ErrorLogin(val error:String):LoginEvents()
         data object Success:LoginEvents()
     }
}