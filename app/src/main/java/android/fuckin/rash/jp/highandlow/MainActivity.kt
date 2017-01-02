package android.fuckin.rash.jp.highandlow

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var imageViewCardMain : ImageView
    lateinit var imageViewCardSub : ImageView
    lateinit var buttonHigh : Button
    lateinit var buttonLow : Button
    lateinit var textViewResult : TextView

    var currentCard = 0
    val rand = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        imageViewCardMain = findViewById(R.id.image_view_main) as ImageView
        imageViewCardSub = findViewById(R.id.image_view_sub) as ImageView

        //最初は7
        currentCard = 0
        setCard(7)

        //button
        buttonHigh = findViewById(R.id.button_high) as Button
        buttonLow = findViewById(R.id.button_low) as Button
        buttonHigh.setOnClickListener(listener)
        buttonLow.setOnClickListener(listener)

        //result
        textViewResult = findViewById(R.id.text_view_result) as TextView
        textViewResult.text = ""

    }

    val listener: (View) -> Unit = {
        val button = it as Button
        //println(button.tag)
        /*
        when (button.tag) {
            getString(R.string.button_high) -> {
                println("HIGH")
            }
            getString(R.string.button_low) -> {
                println("LOW")
            }
        }
        */

        val newCard = rand.nextInt(13) + 1 //1~13
        var result = R.string.draw
        if (newCard != currentCard) {
            when {
                newCard > currentCard -> {
                    //println("high")
                    result = if (button.tag == getString(R.string.button_high)) R.string.win else R.string.lose
                }
                newCard < currentCard -> {
                    //println("low")
                    result = if (button.tag == getString(R.string.button_low)) R.string.win else R.string.lose
                }
                else -> {
                    //ここには来ない
                    //println("draw")
                }
            }
        }

        setCard(newCard)

        //結果表示
        textViewResult.text = getString(result)

        //ボタン無効
        //buttonHigh.isEnabled = false
        //buttonLow.isEnabled = false
        buttonHigh.setTextColor(Color.LTGRAY)
        buttonLow.setTextColor(Color.LTGRAY)

        //時間差で結果消去/ボタン有効
        Handler(mainLooper).postDelayed({
            textViewResult.text = ""
            //buttonHigh.isEnabled = true
            //buttonLow.isEnabled = true
            buttonHigh.setTextColor(ContextCompat.getColor(this, R.color.high))
            buttonLow.setTextColor(ContextCompat.getColor(this, R.color.low))
        }, 2000)
    }

    //カードをセットする
    //  newNumber = 1~13
    fun setCard(cardNumber: Int) {
        val cards = arrayOf(
                0,
                R.drawable.playing_card_spade_a,
                R.drawable.playing_card_spade_2,
                R.drawable.playing_card_spade_3,
                R.drawable.playing_card_spade_4,
                R.drawable.playing_card_spade_5,
                R.drawable.playing_card_spade_6,
                R.drawable.playing_card_spade_7,
                R.drawable.playing_card_spade_8,
                R.drawable.playing_card_spade_9,
                R.drawable.playing_card_spade_10,
                R.drawable.playing_card_spade_j,
                R.drawable.playing_card_spade_q,
                R.drawable.playing_card_spade_k
        )

        println("current=$currentCard")
        imageViewCardSub.setImageResource(cards[currentCard])
        currentCard = cardNumber
        println("current=$currentCard")
        imageViewCardMain.setImageResource(cards[currentCard])
    }

}
