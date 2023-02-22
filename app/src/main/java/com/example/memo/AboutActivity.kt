package com.example.memo

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class AboutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)
    }


    fun goBack(view: View) {
        super.onBackPressed();
    }
    fun goWebsite(view: View){

        val i = Intent(Intent.ACTION_VIEW)
        i.setData(Uri.parse("https://zschie.pl"))
        startActivity(i)
    }
}