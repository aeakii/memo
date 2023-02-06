package com.example.memo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun playSinglePlayer(view: View){
        val gameCatSelector = Intent(this, GameCategorySelectorActivity::class.java)
        startActivity(gameCatSelector)
    }
}