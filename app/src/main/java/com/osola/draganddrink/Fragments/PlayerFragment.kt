package com.osola.draganddrink.Fragments


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.osola.draganddrink.Controllers.PlayerManager
import com.osola.draganddrink.Dialogs.InfoDialog
import com.osola.draganddrink.Model.Player
import com.osola.draganddrink.R
import kotlinx.android.synthetic.main.player_fragment.view.*


class PlayerFragment : Fragment(), PlayerManager.ValueChangeListener {


    private val pm = PlayerManager(this)
    private lateinit var currentPlayerNameView: TextView
    private lateinit var roundView: TextView
    private var activityCallback: listenerGameOver?  = null
    private var totalNumberOfRoundsPlayed = 11

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view =  inflater.inflate(R.layout.player_fragment, container, false)

        view.infoButton.setOnClickListener{
            val infoDialog = InfoDialog(context!!)
            infoDialog.showDialog()
        }


        currentPlayerNameView = view.findViewById(R.id.player_name)
        roundView = view.findViewById(R.id.tv_rounds)
        roundView.text = pm.numerOfRoundsPlayed.toString() + " / " + totalNumberOfRoundsPlayed
        return view
    }


    fun handleSwitchTurn(){
        pm.switchTurn()

        currentPlayerNameView.text = pm.currentPlayer?.name

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
        roundView.text = "${pm.numerOfRoundsPlayed} / ${totalNumberOfRoundsPlayed}"

        if(pm.numerOfRoundsPlayed == totalNumberOfRoundsPlayed){
            activityCallback?.onGameEnded(true, pm.getBiggestPussy()!!)
        }
        Log.d("Changed", newValue.toString())
    }

    interface listenerGameOver {
        fun onGameEnded(gameOver: Boolean, pussyPlayer: Player)
    }


}