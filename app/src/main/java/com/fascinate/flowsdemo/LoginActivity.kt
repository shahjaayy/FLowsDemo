package com.fascinate.flowsdemo

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.fascinate.flowsdemo.databinding.ActivityLoginBinding
import com.google.android.material.snackbar.Snackbar

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: LoginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        binding.button.setOnClickListener{
            viewModel.login(
                binding.edtUsernameLogin.text.toString(),
                binding.edtPasswordLogin.text.toString()
            )
        }

        lifecycleScope.launchWhenStarted {
            viewModel.loginUiState.collect {
                when (it) {
                    is LoginViewModel.LoginStatus.Success -> {
                        Snackbar.make(binding.root, "Login Successfully...", Snackbar.LENGTH_SHORT).show()
                        binding.pbLoading.visibility = View.GONE
                    }
                    is LoginViewModel.LoginStatus.Error -> {
                        Snackbar.make(binding.root, "Some Error...", Snackbar.LENGTH_SHORT).show()
                        binding.pbLoading.visibility = View.GONE
                    }
                    is LoginViewModel.LoginStatus.Loading -> {
                        binding.pbLoading.visibility = View.VISIBLE
                    }
                    else -> Unit
                }
            }
        }
    }
}