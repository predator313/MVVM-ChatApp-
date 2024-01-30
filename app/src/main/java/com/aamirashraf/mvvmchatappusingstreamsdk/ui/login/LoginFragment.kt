package com.aamirashraf.mvvmchatappusingstreamsdk.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import com.aamirashraf.mvvmchatappusingstreamsdk.databinding.FragmentLoginBinding
import com.aamirashraf.mvvmchatappusingstreamsdk.ui.BindingFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment:BindingFragment<FragmentLoginBinding>() {
    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentLoginBinding::inflate

    private val viewModel:LoginViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnConfirm.setOnClickListener{
            setupConnectingUiState()
            viewModel.connectUser(binding.etUsername.text.toString())
        }
        binding.etUsername.addTextChangedListener {
            binding.etUsername.error=null
        }
        subscribeToEvents()
    }
    //getting lots of error in dependency injection
    //dagger is not setup yet

    private fun subscribeToEvents(){
        lifecycleScope.launch {
            viewModel.loginEvent.collect{event->
                when(event){
                    is LoginViewModel.LoginEvents.ErrorInputTooShort->{
                        setupIdleUiState()
                        binding.etUsername.error
                    }
                    is LoginViewModel.LoginEvents.ErrorLogin->{
                        setupIdleUiState()
                        Toast.makeText(
                            requireContext(),
                            event.error,
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is LoginViewModel.LoginEvents.Success->{
                        setupIdleUiState()
                    }
                }

            }
        }
    }

    private fun setupConnectingUiState(){
        binding.progressBar.isVisible=true
        binding.btnConfirm.isEnabled=false
    }
    private fun setupIdleUiState(){
        binding.progressBar.isVisible=false
        binding.btnConfirm.isEnabled=true
    }
}