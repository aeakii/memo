package com.example.memo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class GameCategorySelectorActivity : AppCompatActivity() {
    lateinit var game : Intent
    private val selected : MutableList<Int> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        game = Intent(this, GameActivity::class.java)
        setContentView(R.layout.activity_game_category_selector)
    }
    fun startGame(view:View){
        for (e in selected){
            when(e){
                R.id.buttonCategoryMechanics -> game.putExtra("mechanics",true)
                R.id.buttonCategoryElectricity -> game.putExtra("electricity",true)
                R.id.buttonCategoryMechatronics -> game.putExtra("mechatronics",true)

            }
        }
        game.putExtra("twoPlayers",intent.getBooleanExtra("twoPlayers", false))
        startActivity(game)
    }

    fun selectCards(view:View){

        if(selected.contains(view.id)){
            view.setBackgroundColor(resources.getColor(R.color.purple_200))
            selected.remove(view.id)
        }else{
            selected.add(view.id)
            view.setBackgroundColor(Color.GREEN)
        }

        var startButton1 = findViewById<Button>(R.id.buttonStart)
        if(selected.isEmpty()){

            startButton1.visibility=View.GONE
        }else{
            startButton1.visibility=View.VISIBLE
        }

    }
    fun goBack(view: View){
        super.onBackPressed();
    }

}