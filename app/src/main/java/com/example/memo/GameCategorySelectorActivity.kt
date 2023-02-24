package com.example.memo

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class GameCategorySelectorActivity : AppCompatActivity() {
    lateinit var game : Intent
     private lateinit var selected : MutableList<Int>
     private var started = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game_category_selector)
        game = Intent(this, GameActivity::class.java)
        selected = mutableListOf()
    }

    fun startGame(view:View){
        for (e in selected){
            when(e){
                R.id.buttonCategoryMechanics -> game.putExtra("mechanics",true)
                R.id.buttonCategoryElectricity -> game.putExtra("electricity",true)
                R.id.buttonCategoryMechatronics -> game.putExtra("mechatronics",true)
                R.id.buttonCategoryIT -> game.putExtra("it",true)


            }
        }
        game.putExtra("twoPlayers",intent.getBooleanExtra("twoPlayers", false))
        started=false
        startActivity(game)
    }

    fun selectCards(view:View){
        if(started==false){
            game.putExtra("mechanics",false)
            game.putExtra("electricity",false)
            game.putExtra("mechatronics",false)
            game.putExtra("it",false)
            started=true
        }

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