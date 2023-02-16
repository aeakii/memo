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
    fun play(view: View){
        var activityCategoriesIntent = Intent(this@MainActivity,GameCategorySelectorActivity::class.java)
        activityCategoriesIntent.putExtra("twoPlayers",
            if(view.id==R.id.buttonPlayTwoPlayers)true
            else false
            )
        startActivity(activityCategoriesIntent)
    }

    fun aboutGame(view: View){
        var activityAbout = Intent(this,AboutActivity::class.java)
        startActivity(activityAbout)
    }
}