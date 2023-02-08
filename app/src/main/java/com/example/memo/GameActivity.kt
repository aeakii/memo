package com.example.memo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private val quantityOfCards = 12
    private val imagesOfCards = arrayOfNulls<Int>(quantityOfCards)
    private var cards = arrayOfNulls<Int>(12)
    private var previouslySelectedCardIndex: Int = -1
    private var points: Int = 0
    private var usedCards: MutableList<Int> = mutableListOf()
    private lateinit var previouslySelectedCardComponent: Button
    private val coveredCardImage =
        com.google.android.material.R.drawable.abc_btn_radio_to_on_mtrl_000
    private val cat1 = arrayOf(
        R.drawable.a1,
        R.drawable.a2,
        R.drawable.a3,
        R.drawable.a4,
        R.drawable.a5,
        R.drawable.a6,
        R.drawable.a7,
        R.drawable.a8,
        R.drawable.a9,
        R.drawable.a11,
        R.drawable.a12
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        for (i in 0..cards.size - 1) cards[i] = -2
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        var notSetCards = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
        val notSetImages = cat1.toMutableList()
        for (i in 0..notSetCards.size - 1) {

            if (!notSetCards.contains(i)) {
                continue
            }

            notSetCards.remove(i)
            if (!notSetCards.isEmpty()) {
                var randomElementFromNotSetCards = notSetCards[
                        if (notSetCards.size == 1) 0
                        else Random.nextInt(notSetCards.size - 1)]
                notSetCards.remove(randomElementFromNotSetCards)

                cards[i] = randomElementFromNotSetCards
                cards[randomElementFromNotSetCards] = i

                val randomElementFromNotSetImages = notSetImages[
                        if(notSetImages.size==1)0
                        else Random.nextInt(notSetImages.size -1)
                ]
                notSetImages.remove(randomElementFromNotSetImages)
                imagesOfCards[i]=randomElementFromNotSetImages
                imagesOfCards[randomElementFromNotSetCards]=randomElementFromNotSetImages

            }
        }


    }

    fun selectCard(view: View) {
        if (view is Button) {
            val currentCardIndex = when (view.id) {
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
            view.setCompoundDrawablesWithIntrinsicBounds(null,resources.getDrawable(imagesOfCards[currentCardIndex]!!),null,null)
            view.isClickable=false
            if (previouslySelectedCardIndex == -1) {
                previouslySelectedCardIndex = currentCardIndex
                previouslySelectedCardComponent = view
            } else {
                if (!usedCards.contains(currentCardIndex)) {
                    if ((cards[previouslySelectedCardIndex] == currentCardIndex)) {
                        points++

                        var pointsTxt: TextView = findViewById(R.id.textViewPoints)
                        pointsTxt.text = points.toString()
                        usedCards.add(previouslySelectedCardIndex)
                        usedCards.add(currentCardIndex)
                    } else {
                        view.setCompoundDrawables(null,resources.getDrawable(imagesOfCards[currentCardIndex]!!),null,null)
                        previouslySelectedCardComponent.setCompoundDrawablesWithIntrinsicBounds(null,resources.getDrawable(coveredCardImage),null,null)
                        view.isClickable = true
                        previouslySelectedCardComponent.isClickable = true
                    }
                }

                previouslySelectedCardIndex = -1
            }
        }
    }

}
