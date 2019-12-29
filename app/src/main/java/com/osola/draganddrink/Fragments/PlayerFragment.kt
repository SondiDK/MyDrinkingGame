package com.osola.draganddrink.Fragments


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.osola.draganddrink.Controllers.PlayerManager
import com.osola.draganddrink.R


class PlayerFragment : Fragment() {


    private val pm = PlayerManager()
    private lateinit var currentPlayerNameView: TextView
    private lateinit var roundView: TextView
    private var activityCallback: listenerGameOver?  = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
     val view =  inflater.inflate(R.layout.player_fragment, container, false)
        
        currentPlayerNameView = view.findViewById(R.id.player_name)
        roundView = view.findViewById(R.id.tv_rounds)
        roundView.text = pm.numerOfRoundsPlayed.toString() + " / " + "10"
        return view
    }


    fun handleSwitchTurn(){
        pm.switchTurn()
        if(pm.numerOfRoundsPlayed == 10){
            activityCallback?.onGameEnded(true)
        }
        currentPlayerNameView.text = pm.currentPlayer?.name
        roundView.text = pm.numerOfRoundsPlayed.toString() + " / " + "10"
    }

    fun setGameOverListener(callback: listenerGameOver) {
        this.activityCallback = callback

    }

    fun setPlayernames(names: ArrayList<String>) {
        for (name in names) {
            pm.addPlayer(name);

        }

        pm.startGame()
        currentPlayerNameView.text = pm.currentPlayer?.name
    }

    fun addToPlayer(didIt: Boolean){
        if (didIt) {
            this.pm.currentPlayer?.addToChallengesAccpted()
        } else {
            this.pm.currentPlayer?.addToChallengesDenied()
        }
    }
    interface listenerGameOver {
        fun onGameEnded(gameOver: Boolean)
    }




}