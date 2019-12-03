package com.osola.draganddrink.Model
/**
 * Created by Oliver on 07-10-2018.
 */
class PlayerManager() {

    //ToDO Lav fragment i MainAktivity der holder styr på playerLogik??

    var playerList: MutableList<Player> = mutableListOf<Player>()
    var currentPlayer: Player? = null
    var playerNumber: Int = 0

    fun addPlayer(playerName: String) {
        val player = Player(playerName)
        playerList.add(player)
    }

    fun startGame(){
        currentPlayer = playerList[playerNumber]
    }

    fun switchTurn () {
        if(playerNumber>=playerList.size-1){
          playerNumber = 0
        }else{
            playerNumber++;
        }
        currentPlayer = playerList[playerNumber]
    }
}