package com.aamirashraf.mvvmchatappusingstreamsdk.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.aamirashraf.mvvmchatappusingstreamsdk.MIN_USERNAME_LEN
import com.aamirashraf.mvvmchatappusingstreamsdk.R
import com.aamirashraf.mvvmchatappusingstreamsdk.databinding.FragmentLoginBinding
import com.aamirashraf.mvvmchatappusingstreamsdk.navigateSafely
import com.aamirashraf.mvvmchatappusingstreamsdk.ui.BindingFragment
import dagger.Module
import dagger.Provides
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Singleton


@AndroidEntryPoint
class LoginFragment : BindingFragment<FragmentLoginBinding>() {

    override val bindingInflater: (LayoutInflater) -> ViewBinding
        get() = FragmentLoginBinding::inflate

    private val viewModel: LoginViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnConfirm.setOnClickListener {
            setupConnectingUiState()
            viewModel.connectUser(binding.etUsername.text.toString())
        }

        binding.etUsername.addTextChangedListener {
            binding.etUsername.error = null
        }

        subscribeToEvents()
    }

    private fun subscribeToEvents() {
        lifecycleScope.launchWhenStarted {
            viewModel.loginEvent.collect { event ->
                when(event) {
                    is LoginViewModel.LogInEvent.ErrorInputTooShort -> {
                        setupIdleUiState()
                        binding.etUsername.error = getString(R.string.error_username_too_short, MIN_USERNAME_LEN)
                    }
                    is LoginViewModel.LogInEvent.ErrorLogIn -> {
                        setupIdleUiState()
                        Toast.makeText(
                            requireContext(),
                            event.error,
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    is LoginViewModel.LogInEvent.Success -> {
                        setupIdleUiState()
                        findNavController().navigateSafely(R.id.action_loginFragment_to_channelFragment)
                    }
                }
            }
        }
    }

    private fun setupConnectingUiState() {
        binding.progressBar.isVisible = true
        binding.btnConfirm.isEnabled = false
    }

    private fun setupIdleUiState() {
        binding.progressBar.isVisible = false
        binding.btnConfirm.isEnabled = true
    }
}