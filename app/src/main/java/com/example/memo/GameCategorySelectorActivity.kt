package com.example.memo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class GameCategorySelectorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game_category_selector)
    }
    fun startGame(view:View){
        val game = Intent(this, GameActivity::class.java)
        game.putExtra("twoPlayers",intent.getBooleanExtra("twoPlayers",false))
        var category = when (view.id) {
            R.id.buttonCategoryElectricity -> 1
            R.id.buttonCategoryMechatronics -> 2
            R.id.buttonCategoryMechanics -> 3
            else -> 0
        }
        game.putExtra("category",category)
        startActivity(game)
    }
    fun goBack(view: View){
        super.onBackPressed();
    }
}