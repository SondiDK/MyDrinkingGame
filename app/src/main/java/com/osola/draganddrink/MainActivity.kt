package com.osola.draganddrink

import android.app.AlertDialog
import android.app.FragmentManager
import android.content.ClipData
import android.content.ClipDescription
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.DragEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.osola.draganddrink.Enums.CardType
import com.osola.draganddrink.R
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnDragListener, View.OnTouchListener, PlayerFragment.Listener {
    override fun onButtonClick() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    val cardDeck = CardController()

    private lateinit var drinkCard: ImageView
    private lateinit var gameCard: ImageView
    private lateinit var challengeCard: ImageView
    private lateinit var type: CardType

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        droPlace.setBackgroundColor(Color.BLUE)

        //listerners on card
        drinkCard = findViewById(R.id.drinkCard) as ImageView
        gameCard = findViewById(R.id.gameCard) as ImageView
        challengeCard = findViewById(R.id.challengeCard) as ImageView

        drinkCard.setOnTouchListener(this)
        gameCard.setOnTouchListener(this)
        challengeCard.setOnTouchListener(this)

        droPlace.setOnDragListener(this)

    }

    fun mahTest() {
        val articleFrag = supportFragmentManager.findFragmentById(R.id.playerFrag) as PlayerFragment?
        articleFrag?.testPrint()

    }





    override fun onDrag(view: View?, event: DragEvent?): Boolean {
        val action = event?.action
        when (action) {
            DragEvent.ACTION_DRAG_STARTED -> {
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                this.droPlace.setBackgroundColor(Color.BLUE)
            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                this.droPlace.setBackgroundColor(Color.GREEN)
            }
            DragEvent.ACTION_DROP -> {
                this.cardDeck.showCard(this, this.type)
                this.mahTest()
                return true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                this.droPlace.setBackgroundColor(Color.BLUE)
                return true
            }
            else -> {
            }
        }// Executed after startDrag() is called.
        return true
    }
    override fun onTouch(view: View, motionEvent: MotionEvent): Boolean {
        var shadow: View.DragShadowBuilder? = null

        when (view) {
            drinkCard-> {   shadow = View.DragShadowBuilder(drinkCard)
                type = CardType.DRINK
            }
            gameCard -> { shadow = View.DragShadowBuilder(gameCard)
                type = CardType.GAME
            }
            challengeCard -> {  shadow = View.DragShadowBuilder(challengeCard)
                type = CardType.CHALLENGE
            }
        }
        val data = ClipData.newPlainText("", "")
        view.startDragAndDrop(data, shadow, null, 0)
        return false
    }
}
