package com.osola.draganddrink.Controllers

import com.osola.draganddrink.Model.Player

/**
 * Created by Oliver on 07-10-2018.
 */
class PlayerManager() {

    private var playerList: MutableList<Player> = mutableListOf()
    private var currentPlayer: Player? = null
    private var playerNumber: Int = 0
    private var numerOfRoundsPlayed: Int = 1

    fun addPlayer(playerName: String) {
        val player = Player(playerName)
        this.playerList.add(player)
    }

    fun startGame(){
        this.currentPlayer = this.playerList[playerNumber]
    }

    fun switchTurn () {

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