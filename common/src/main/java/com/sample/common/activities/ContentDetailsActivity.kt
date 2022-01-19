package com.sample.common.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sample.common.R

class ContentDetailsActivity : AppCompatActivity() {

   private var description : String? =""
   private var title : String? = ""
   private var icon : String? = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Retrieve intent coming data from MainActivity.java
        if(intent != null) {
            description = intent.getStringExtra("description")
            title = intent.getStringExtra("title")
            icon = intent.getStringExtra("icon")
        }

        // Pass the data to FragmentDetails to display it
        val fragmentB = supportFragmentManager.findFragmentById(R.id.fragmentB) as FragmentDetails?
        fragmentB?.updateDisplayDetails(title.toString(), description.toString(), icon.toString())

    }
}
