package com.sample.common.activities

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import com.sample.common.R
import kotlinx.android.synthetic.main.fragment_b.*

import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread


class FragmentDetails : Fragment() {

	var valueURL : String = ""
	lateinit var progressbar: ProgressBar

	override fun onAttach(context: Context) {
		super.onAttach(context)
	}
	override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

		return inflater.inflate(R.layout.fragment_b, container, false)
	}

	fun updateDisplayDetails(title: String, description: String, icon: String) {


		doAsync {
			uiThread {
				    myTitle.text = title
					valueURL = icon
					val webSettings = myDetailsImageView.settings
					webSettings.javaScriptEnabled = true
					myDetailsImageView.loadUrl(valueURL)
				if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
					myDescription.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_COMPACT));
				} else {
					myDescription.setText(Html.fromHtml(description));
				}
				myDescription.setMovementMethod(LinkMovementMethod.getInstance());
				}
			}


		}



}
