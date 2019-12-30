package com.osola.draganddrink.Controllers

import com.osola.draganddrink.Model.Player
import kotlin.properties.Delegates

/**
 * Created by Oliver on 07-10-2018.
 */
class PlayerManager(listener: ValueChangeListener) {

    interface ValueChangeListener {
        fun onValueChanged(newValue: Int)

    }

    private var playerList: MutableList<Player> = mutableListOf()
    var currentPlayer: Player? = null
    private var playerNumber: Int = 0


    var numerOfRoundsPlayed: Int by Delegates.observable(initialValue = 0, onChange = {
        prop, old, new  -> listener.onValueChanged(new)
    })

    fun addPlayer(playerName: String) {
        val player = Player(playerName)
        this.playerList.add(player)
    }

    fun startGame() {
        this.currentPlayer = this.playerList[playerNumber]
    }

    fun switchTurn() {

        if(this.playerNumber >= this.playerList.lastIndex) {
            this.numerOfRoundsPlayed++
            this.playerNumber = 0
        } else {
            this.playerNumber++
        }
        this.currentPlayer = this.playerList[playerNumber]
    }

    fun getBiggestPussy(): Player? {

       return playerList.maxBy { it.challengesDenied }

    }
}