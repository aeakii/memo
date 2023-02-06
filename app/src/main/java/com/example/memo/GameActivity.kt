package com.example.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private var cards = arrayOf(0,1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
    private var selectedcard: Int = -1
    private var points: Int = 0
    private  var usedCards : MutableList<Int> =mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        for(i in 0 .. cards.size-1)cards[i]=-2
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        var notSetCards=mutableListOf(0,1,2,3,4,5,6,7,8,9,10,11)
        for(i in 0 .. notSetCards.size-1) {

            if(!notSetCards.contains(i)) {
                continue
            }

                notSetCards.remove(i)
            if (!notSetCards.isEmpty()) {
                var randomElementFromNotSetCards = notSetCards[
                    if(notSetCards.size==1) 0
                    else Random.nextInt(notSetCards.size - 1)]
                notSetCards.remove(randomElementFromNotSetCards)

                cards[i] = randomElementFromNotSetCards
                cards[randomElementFromNotSetCards]=i
            }
        }


    }

    fun selectCard(view: View) {

        val currentCard = when (view.id) {
            R.id.imageButton1 -> 0
            R.id.imageButton2 -> 1
            R.id.imageButton3 -> 2
            R.id.imageButton4 -> 3
            R.id.imageButton5 -> 4
            R.id.imageButton6 -> 5
            R.id.imageButton7 -> 6
            R.id.imageButton8 -> 7
            R.id.imageButton9 -> 8
            R.id.imageButton10 -> 9
            R.id.imageButton11-> 10
            R.id.imageButton12 -> 11
            else -> -1
        }
        var cardstoseeindebugger = cards
        if (selectedcard == -1) selectedcard = currentCard
        else {
            if ((cards[selectedcard]==currentCard) and !usedCards.contains(currentCard)) {
                points++
                var pointsTxt: TextView = findViewById(R.id.textViewPoints)
                pointsTxt.text = points.toString()
                usedCards.add(selectedcard)
                usedCards.add(currentCard)
            }
            selectedcard = -1
        }
    }

}
