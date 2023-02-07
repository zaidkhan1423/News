package com.example.news.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.example.news.R

class DescriptionActivity : AppCompatActivity() {

    lateinit var webView: WebView
    var url: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_description)

        webView = findViewById(R.id.webView)

        if (intent != null) {

            val bundle: Bundle? = intent.extras
            url = bundle?.getString("URL")

//            webView.settings.javaScriptEnabled = true
            webView.loadUrl(url.toString())
        }

    }
}