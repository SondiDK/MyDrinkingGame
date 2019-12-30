package com.osola.draganddrink.Model
/**
 * Created by Oliver on 07-10-2018.
 */
data class Player(val name: String)
{
     var sipsTotal: Int = 0
     var challengesAccepted: Int = 0
     var challengesDenied: Int = 0


    fun addSips(sips: Int){
        this.sipsTotal += sips
    }

    fun addToChallengesAccpted() {
        this.challengesAccepted++

    }

    fun addToChallengesDenied() {
        this.challengesDenied++
    }

    fun resetPlayerStats() {
        this.challengesAccepted = 0
        this.challengesDenied = 0
    }

}