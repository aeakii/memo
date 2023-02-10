package com.example.memo

import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private val quantityOfCards = 12
    private val dataOfCards = arrayOfNulls<Card>(quantityOfCards)
    private var indexOfTheSecondCard = arrayOfNulls<Int>(12)
    private var previouslySelectedCardIndex: Int = -1
    private var points: Int = 0
    private var selectedCards: MutableList<Int> = mutableListOf()
    private lateinit var previouslySelectedCardComponent: ImageView
    private val coveredCardImage =
        com.google.android.material.R.drawable.abc_btn_radio_to_on_mtrl_000


    override fun onCreate(savedInstanceState: Bundle?) {
        if(intent.getBooleanExtra("twoPlayers",false)) {
            val txtPlayer2 = findViewById<View>(R.id.textViewPointsPlayerBlue)
            //txtPlayer2.visibility = View.GONE
        }

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
        if (view is ImageView) {

            displayCard(view,false)
            if (previouslySelectedCardIndex == -1) {
                previouslySelectedCardIndex = getCardIndex(view)
                previouslySelectedCardComponent = view
            } else {
                if (!selectedCards.contains(getCardIndex(view))) {
                    if ((indexOfTheSecondCard[previouslySelectedCardIndex] == getCardIndex(view))) {
                        points++

                        var pointsTxt: TextView = findViewById(R.id.textViewPointsPlayerRed)
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

    fun displayCard(cardView : ImageView, cover : Boolean ){
        cardView.isClickable = cover
        if(cover){
            cardView.setImageDrawable(resources.getDrawable(coveredCardImage))
            //cardView.text =""
        }else{
            var indexOfTheCurrentCard = getCardIndex(cardView)
            if(indexOfTheCurrentCard < indexOfTheSecondCard[indexOfTheCurrentCard]!!){
                //cardView.setCompoundDrawablesWithIntrinsicBounds(null,resources.getDrawable(dataOfCards[indexOfTheCurrentCard]!!.image),null,null)
                cardView.setImageDrawable(resources.getDrawable(dataOfCards[indexOfTheCurrentCard]!!.image))
            }
            else {
                //cardView.text = dataOfCards[indexOfTheCurrentCard]!!.pronoun + " "+ dataOfCards[indexOfTheCurrentCard]!!.name

            }

        }
    }

    fun getCardIndex(view: View) : Int {
        return when (view.id) {
            R.id.imageView1 -> 0
            R.id.imageView2 -> 1
            R.id.imageView3 -> 2
            R.id.imageView4 -> 3
            R.id.imageView5 -> 4
            R.id.imageView6 -> 5
            R.id.imageView7 -> 6
            R.id.imageView8 -> 7
            R.id.imageView9 -> 8
            R.id.imageView10 -> 9
            R.id.imageView11 -> 10
            R.id.imageView12 -> 11
            else -> -1
        }
    }

}
