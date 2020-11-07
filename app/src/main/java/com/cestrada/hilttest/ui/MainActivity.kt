package com.cestrada.hilttest.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

import com.cestrada.hilttest.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

}

