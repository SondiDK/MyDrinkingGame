package com.osola.draganddrink.Fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.osola.draganddrink.Controllers.PlayerManager
import com.osola.draganddrink.R


class PlayerFragment : Fragment(), PlayerManager.ValueChangeListener {


    private val pm = PlayerManager(this)
    private lateinit var currentPlayerNameView: TextView
    private lateinit var roundView: TextView
    private var activityCallback: listenerGameOver?  = null


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
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
        roundView.text = "${pm.numerOfRoundsPlayed} / 10"
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

    override fun onValueChanged(newValue: Int) {
        //hmm not sure if this is the way but kinda cool
        Log.d("Changed", newValue.toString())
    }

    interface listenerGameOver {
        fun onGameEnded(gameOver: Boolean)
    }




}