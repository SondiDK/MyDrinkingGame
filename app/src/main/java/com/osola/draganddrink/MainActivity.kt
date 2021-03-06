package com.osola.draganddrink

import android.content.ClipData
import android.os.Bundle
import android.view.DragEvent
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.osola.draganddrink.Controllers.CardController
import com.osola.draganddrink.Dialogs.DialogCard
import com.osola.draganddrink.Dialogs.GameOverDialog
import com.osola.draganddrink.Enums.CardType
import com.osola.draganddrink.Fragments.PlayerFragment
import com.osola.draganddrink.models.CardResult
import com.osola.draganddrink.models.Player
import com.osola.draganddrink.R.color.black
import com.osola.draganddrink.R.color.game_color
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity(), View.OnDragListener, View.OnTouchListener,
    DialogCard.CardListener, PlayerFragment.listenerGameOver, GameOverDialog.GameOverListener {



    private val cardDeck = CardController()
    private lateinit var drinkCard: ImageView
    private lateinit var gameCard: ImageView
    private lateinit var challengeCard: ImageView
    private lateinit var type: CardType
    private lateinit var  playerFragment: PlayerFragment


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //listerners on card
        this.drinkCard = findViewById(R.id.drinkCard) as ImageView
        this.gameCard = findViewById(R.id.gameCard) as ImageView
        this.challengeCard = findViewById(R.id.challengeCard) as ImageView

        this.drinkCard.setOnTouchListener(this)
        this.gameCard.setOnTouchListener(this)
        this.challengeCard.setOnTouchListener(this)

        this.playerFragment = supportFragmentManager.findFragmentById(R.id.playerFrag) as PlayerFragment
        this.playerFragment.setGameOverListener(this)

        dropZone.setOnDragListener(this)

        val names = intent.getStringArrayListExtra(PLAYER_NAMES_KEY)
        val numberOfRounds = intent.getIntExtra(NUMBER_OF_ROUNDS, 10)
        this.playerFragment.setPlayernames(names)
        this.playerFragment.setNumberOfRounds(numberOfRounds)

    }



    override fun onDrag(view: View?, event: DragEvent?): Boolean {
        val action = event?.action
        when (action) {
            DragEvent.ACTION_DRAG_STARTED -> {
                dragInfoView.visibility = View.VISIBLE
            }
            DragEvent.ACTION_DRAG_EXITED -> {
                dragInfoView.setBackgroundResource(R.drawable.dotted_border)
                dragInfoView.setTextColor(resources.getColor(black, null))

            }
            DragEvent.ACTION_DRAG_ENTERED -> {
                dragInfoView.setBackgroundResource(R.drawable.dotted_border_green)
                dragInfoView.setTextColor(resources.getColor(game_color, null))
            }
            DragEvent.ACTION_DROP -> {
                this.cardDeck.showCard(this, this.type, this) //todo set in constructer
                dragInfoView.visibility = View.INVISIBLE
                return true
            }
            DragEvent.ACTION_DRAG_ENDED -> {
                dragInfoView.setBackgroundResource(R.drawable.dotted_border)
                dragInfoView.visibility = View.INVISIBLE
                dragInfoView.setTextColor(resources.getColor(black, null))
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

    override fun onGameEnded(gameOver: Boolean, pussyPlayer: Player) {
        val gameOverDialog = GameOverDialog(this, this)
        gameOverDialog.showDialog(pussyPlayer)

    }

    override fun onCardClick(result: CardResult) {
        this.playerFragment.addToPlayer(result.acceptedCard)
        this.playerFragment.handleSwitchTurn()

    }

    override fun onPlayAgainClick() {
        this.playerFragment.playAgainWithSamePlayers()
    }

    override fun onBackToMenuClick() {
        this.finish()
    }

}
