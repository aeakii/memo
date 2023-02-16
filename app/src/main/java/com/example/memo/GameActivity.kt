package com.example.memo

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private val quantityOfCards = 12
    private val dataOfCards = arrayOfNulls<Card>(quantityOfCards)
    private var indexOfTheSecondCard = arrayOfNulls<Int>(12)
    private var previouslySelectedCardIndex: Int = -1
    private var pointsPlayer1: Int = 0
    private var pointsPlayer2: Int = 0

    private var selectedCards: MutableList<Int> = mutableListOf()
    private  var selectedCardComponent1: TextView? = null
    private  var selectedCardComponent2: TextView? = null
    private lateinit var selectedCardComponent1Timer: TextView
    private lateinit var selectedCardComponent2Timer: TextView

    private val coveredCardImage = R.drawable.logo
    private var timerNotStarted: Boolean = true
    private var player2turn: Boolean = false
    private var players2mode: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        if (intent.getBooleanExtra("twoPlayers", false)) {
            players2mode = true
            val txtPlayer2: TextView = findViewById(R.id.textViewPointsPlayerBlue)
            txtPlayer2.visibility = View.VISIBLE
        }

        restartGame()


    }

    fun selectCard(view: View) {

        if (view is TextView) {
            if (!timerNotStarted) coverCurrentCards()
            selectedCardComponent2 = view
            displayCard(view, false)
            if (previouslySelectedCardIndex == -1) {
                previouslySelectedCardIndex = getCardIndex(view)
                selectedCardComponent1 = view
            } else {
                //if (!timerNotStarted) coverCurrentCards()
                if (!selectedCards.contains(getCardIndex(view))) {
                    if ((indexOfTheSecondCard[previouslySelectedCardIndex] == getCardIndex(view))) {
                        addPoints()
                        selectedCards.add(previouslySelectedCardIndex)
                        selectedCards.add(getCardIndex(view))
                        view.setShadowLayer(0F, 0F, 0F, 0xFFFFFF)
                        selectedCardComponent1!!.setShadowLayer(0F, 0F, 0F, 0xFFFFFF)
                    } else {

                        selectedCardComponent1Timer= selectedCardComponent1!!
                        selectedCardComponent2Timer=selectedCardComponent2!!
                        selectedCardComponent1=null
                        selectedCardComponent2=null
                        switchPlayerTurn()
                        timerNotStarted = false
                        timer.start()
                    }
                }

                previouslySelectedCardIndex = -1
            }
        }
    }

    fun displayCard(cardView: TextView, cover: Boolean) {
        cardView.isClickable = cover
        if (cover) {
            cardView.setBackgroundResource(coveredCardImage)
            cardView.text = ""
        } else {
            var indexOfTheCurrentCard = getCardIndex(cardView)
            if (indexOfTheCurrentCard < indexOfTheSecondCard[indexOfTheCurrentCard]!!) {
                cardView.setBackgroundResource(dataOfCards[indexOfTheCurrentCard]!!.image)
            } else {
                cardView.text =
                    dataOfCards[indexOfTheCurrentCard]!!.pronoun + " " + dataOfCards[indexOfTheCurrentCard]!!.name
                cardView.setBackgroundResource(0)

            }

        }
    }

    fun getCardIndex(view: View): Int {
        return when (view.id) {
            R.id.textView1 -> 0
            R.id.textView2 -> 1
            R.id.textView3 -> 2
            R.id.textView4 -> 3
            R.id.textView5 -> 4
            R.id.textView6 -> 5
            R.id.textView7 -> 6
            R.id.textView8 -> 7
            R.id.textView9 -> 8
            R.id.textView10 -> 9
            R.id.textView11 -> 10
            R.id.textView12 -> 11
            else -> -1
        }
    }

    var timer = object : CountDownTimer(1000, 10) {
        override fun onTick(millisUntilFinished: Long) {

        }

        override fun onFinish() {
            coverCurrentCards()
            timerNotStarted = true
        }
    }

    private fun coverCurrentCards() {
        if (selectedCardComponent1Timer != selectedCardComponent1 && selectedCardComponent2 != selectedCardComponent1Timer)
            displayCard(selectedCardComponent1Timer, true)
        if (selectedCardComponent2Timer != selectedCardComponent1 && selectedCardComponent2 != selectedCardComponent2Timer)
            displayCard(selectedCardComponent2Timer, true)

    }

    fun goBack(view: View) {
        super.onBackPressed();
    }

    private fun switchPlayerTurn() {
        if (!players2mode) return
        val txtPlayer1: TextView = findViewById(R.id.textViewPointsPlayerRed)
        val txtPlayer2: TextView = findViewById(R.id.textViewPointsPlayerBlue)
        if (player2turn) {
            txtPlayer1.setBackgroundColor(
                Color.RED
            )
            txtPlayer2.setBackgroundColor(Color.WHITE)
        } else {
            txtPlayer1.setBackgroundColor(Color.WHITE)
            txtPlayer2.setBackgroundColor(Color.BLUE)
        }
        player2turn = !player2turn

    }

    private fun addPoints() {
        var pointsTxt: TextView = findViewById(
            if (player2turn) R.id.textViewPointsPlayerBlue
            else R.id.textViewPointsPlayerRed
        )
        pointsTxt.text = "points: " + if (player2turn) {
            ++pointsPlayer1
        } else {
            ++pointsPlayer2
        }.toString()

    }

    fun restartGame(view: View){
         intent = getIntent()
        startActivity(intent)
    }
    fun restartGame(){
        for (i in 0..indexOfTheSecondCard.size - 1) indexOfTheSecondCard[i] = -2

        var notSetCardIndexes = mutableListOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11)
        val notUsedCards = mutableListOf<Card>()
        val gameLayout : LinearLayout = findViewById(R.id.gameLayout)
           if (intent.getBooleanExtra("electricity", false)){
                    gameLayout.background=resources.getDrawable(R.drawable.electricity1)
                    notUsedCards+= electricityCards.toMutableList()
                }
        if (intent.getBooleanExtra("mechatronics", false)){
            gameLayout.background=resources.getDrawable(R.drawable.mechatronics)
            notUsedCards+= mechatronicsCards.toMutableList()
        }
        if (intent.getBooleanExtra("mechanics", false)){
            gameLayout.background=resources.getDrawable(R.drawable.mechanics)
            notUsedCards+= mechanicCards.toMutableList()
        }



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
                        if (notUsedCards.size == 1) 0
                        else Random.nextInt(notUsedCards.size - 1)
                ]
                notUsedCards.remove(randomElementFromNotUsedCards)
                dataOfCards[i] = randomElementFromNotUsedCards
                dataOfCards[randomElementFromNotSetCardIndexes] = randomElementFromNotUsedCards

            }
        }
    }
}
