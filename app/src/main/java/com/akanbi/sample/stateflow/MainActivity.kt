package com.akanbi.sample.stateflow

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.asLiveData
import com.akanbi.sample.stateflow.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel by viewModels<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupListener()
        setupObservables()
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private fun setupObservables() {
        viewModel.uiStateFlow.asLiveData().observe(this) {
            with(binding) {
                when (it) {
                    ResultState.Loading -> {
                        flipperState.displayedChild = 0
                        Toast.makeText(this@MainActivity, "Loading Flow", Toast.LENGTH_SHORT).show()
                    }
                    ResultState.Success -> {
                        flipperState.displayedChild = 1
                        imageStateFlow.setImageDrawable(getDrawable(R.drawable.heisenberg))
                        Toast.makeText(this@MainActivity, "Success Flow", Toast.LENGTH_SHORT).show()
                    }
                    ResultState.Error -> {
                        flipperState.displayedChild = 1
                        imageStateFlow.setImageDrawable(getDrawable(R.drawable.jason))
                        Toast.makeText(this@MainActivity, "Error Flow", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        flipperState.displayedChild = 0
                        loadingState.visibility = View.GONE
                    }
                }
            }
        }
    }

    private fun setupListener() {
        binding.doSomethingButton.setOnClickListener {
            viewModel.doSomething()
        }
    }
}