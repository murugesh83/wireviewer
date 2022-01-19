package com.sample.simpsonscharacter

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi

class MainActivity : com.sample.common.activities.MainActivity() {

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.activity_main)
    }
}