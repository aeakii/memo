package com.example.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private val quantityOfCards = 12
    private val dataOfCards = arrayOfNulls<Card>(quantityOfCards)
    private var indexOfTheSecondCard = arrayOfNulls<Int>(12)
    private var previouslySelectedCardIndex: Int = -1
    private var points: Int = 0
    private var selectedCards: MutableList<Int> = mutableListOf()
    private lateinit var previouslySelectedCardComponent: Button
    private val coveredCardImage =
        com.google.android.material.R.drawable.abc_btn_radio_to_on_mtrl_000


    override fun onCreate(savedInstanceState: Bundle?) {
        for (i in 0..indexOfTheSecondCard.size - 1) indexOfTheSecondCard[i] = -2
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        var notSetCardIndexes = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
        val notUsedCards = mechatronicsCards.toMutableList()
        for (i in 0..notSetCardIndexes.size - 1) {

            if (!notSetCardIndexes.contains(i)) {
                continue
            }

            notSetCardIndexes.remove(i)
            if (!notSetCardIndexes.isEmpty()) {
                var randomElementFromNotSetCardIndexes = notSetCardIndexes[
                        if (notSetCardIndexes.size == 1) 0
                        else Random.nextInt(notSetCardIndexes.size - 1)]
                notSetCardIndexes.remove(randomElementFromNotSetCardIndexes)

                indexOfTheSecondCard[i] = randomElementFromNotSetCardIndexes
                indexOfTheSecondCard[randomElementFromNotSetCardIndexes] = i

                val randomElementFromNotUsedCards = notUsedCards[
                        if(notUsedCards.size==1)0
                        else Random.nextInt(notUsedCards.size -1)
                ]
                notUsedCards.remove(randomElementFromNotUsedCards)
                dataOfCards[i]=randomElementFromNotUsedCards
                dataOfCards[randomElementFromNotSetCardIndexes]=randomElementFromNotUsedCards

            }
        }


    }

    fun selectCard(view: View) {
        if (view is Button) {

            displayCard(view,false)
            if (previouslySelectedCardIndex == -1) {
                previouslySelectedCardIndex = getCardIndex(view)
                previouslySelectedCardComponent = view
            } else {
                if (!selectedCards.contains(getCardIndex(view))) {
                    if ((indexOfTheSecondCard[previouslySelectedCardIndex] == getCardIndex(view))) {
                        points++

                        var pointsTxt: TextView = findViewById(R.id.textViewPoints)
                        pointsTxt.text = points.toString()
                        selectedCards.add(previouslySelectedCardIndex)
                        selectedCards.add(getCardIndex(view))
                    } else {
                        displayCard(view,true)
                        displayCard(previouslySelectedCardComponent,true)
                    }
                }

                previouslySelectedCardIndex = -1
            }
        }
    }

    fun displayCard(cardView : Button, cover : Boolean ){
        cardView.isClickable = cover
        if(cover){
            cardView.setCompoundDrawablesWithIntrinsicBounds(null,resources.getDrawable(coveredCardImage),null,null)
            cardView.text =""
        }else{
            var indexOfTheCurrentCard = getCardIndex(cardView)
            if(indexOfTheCurrentCard < indexOfTheSecondCard[indexOfTheCurrentCard]!!)
                cardView.setCompoundDrawablesWithIntrinsicBounds(null,resources.getDrawable(dataOfCards[indexOfTheCurrentCard]!!.image),null,null)
            else {
                cardView.text = dataOfCards[indexOfTheCurrentCard]!!.pronoun + " "+ dataOfCards[indexOfTheCurrentCard]!!.name

            }

        }
    }

    fun getCardIndex(view: View) : Int {
        return when (view.id) {
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
            R.id.imageButton11 -> 10
            R.id.imageButton12 -> 11
            else -> -1
        }
    }

}
