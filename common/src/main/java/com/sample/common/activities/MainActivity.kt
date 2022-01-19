package com.sample.common.activities

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.sample.common.R
import com.smartherd.multiplescreensupport.FragmentCommunicatorInterface
import kotlinx.android.synthetic.main.activity_main2.*

open class MainActivity : AppCompatActivity(), FragmentCommunicatorInterface {

	private var mIsDualPane = false

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main2)

		fragmentA.view?.setOnClickListener {
			val intent = Intent(this, ContentDetailsActivity::class.java)
			startActivity(intent)
		}

        val fragmentBView = findViewById<View>(R.id.fragmentB)
        mIsDualPane = fragmentBView?.visibility == View.VISIBLE
	}

	override fun updateDisplayDetails(title: String, description: String, icon: String) {

        if (mIsDualPane) { // If we are in Tablet
            val fragmentB = supportFragmentManager.findFragmentById(R.id.fragmentB) as FragmentDetails?
            fragmentB?.updateDisplayDetails(title, description, icon)
        } else { // When we are in Smart phone
            val intent = Intent(this, ContentDetailsActivity::class.java)
            intent.putExtra("title", title)
            intent.putExtra("description", description)
            intent.putExtra("icon", icon)
            startActivity(intent)
        }
	}


}
