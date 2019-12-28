package com.osola.draganddrink.Model
/**
 * Created by Oliver on 07-10-2018.
 */
class PlayerManager() {

    //ToDO Lav fragment i MainAktivity der holder styr på playerLogik??

    var playerList: MutableList<Player> = mutableListOf()
    var currentPlayer: Player? = null
    var playerNumber: Int = 0
    var numerOfRoundsPlayed: Int = 0

    fun addPlayer(playerName: String) {
        val player = Player(playerName)
        this.playerList.add(player)
    }

    fun startGame(){
        this.currentPlayer = this.playerList[playerNumber]
    }

    fun switchTurn () {

        if(this.playerNumber >= this.playerList.size-1) {
            this.numerOfRoundsPlayed++
          this.playerNumber = 0
        } else {
            this.playerNumber++
        }
        this.currentPlayer = this. playerList[playerNumber]
    }
}