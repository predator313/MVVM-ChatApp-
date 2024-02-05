package com.aamirashraf.mvvmchatappusingstreamsdk

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aamirashraf.mvvmchatappusingstreamsdk.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //there is some error from stream side
        //so there is issue in the project
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}