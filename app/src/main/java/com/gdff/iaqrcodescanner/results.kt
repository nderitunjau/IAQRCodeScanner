package com.gdff.iaqrcodescanner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import com.gdff.iaqrcodescanner.databinding.ActivityMainBinding

class results : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        val webView = findViewById<WebView>(R.id.webView)
        // Retrieve the URL from the Intent extras or wherever you're storing it
        val url = intent.getStringExtra("URL_KEY") ?: ""
        // Load the URL in the WebView
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(url)
    }
}
