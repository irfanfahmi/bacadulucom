package com.descolab.bacadulucom.detailArticle

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import com.descolab.bacadulucom.R

class detailArticleActivity : AppCompatActivity() {
    private lateinit var webView: WebView

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_article)
        webView = findViewById(R.id.webview)
        webView.settings.setJavaScriptEnabled(true)
        val dataUrl :String? = intent.getStringExtra("url")

        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView?, url: String?): Boolean {
                view?.loadUrl(url.toString())
                return true
            }
        }
        if (dataUrl != null) {
            webView.loadUrl(dataUrl)
        }else{
            val builder = AlertDialog.Builder(this)
            builder.setTitle(getString(R.string.app_name))
            builder.setMessage(getString(R.string.url_notfound))
            builder.setCancelable(false)
            builder.setPositiveButton("OK"){dialog, which ->
                super.onResume()
            }
            val dialog: AlertDialog = builder.create()
            dialog.show()
        }

    }
}