package com.fascinate.flowsdemo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.fascinate.flowsdemo.databinding.ActivityCounterBinding

class CounterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCounterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCounterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewModel: CounterViewModel = ViewModelProvider(this)[CounterViewModel::class.java]

        // launch() is actually collect the information that are emitted even when the app is in Background
        // So, it cause crash sometimes, so always use launchWhenStarted().
        lifecycleScope.launchWhenStarted {
            viewModel.startCounter.collect {
                binding.tvCounter.text = it.toString()
            }
            binding.tvCounter.text = "Completed..."
            val i = Intent(applicationContext, LoginActivity::class.java)
            startActivity(i)

        }

    }
}