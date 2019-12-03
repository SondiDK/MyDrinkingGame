package com.osola.draganddrink.Model

import com.osola.draganddrink.Enums.CardType

import java.util.*

class CardDeck {

    fun getCard(type: CardType):Card?{

        when (type){
            CardType.DRINK -> { return DrinkCard()}
           CardType.GAME -> { return ChallengeCard() }
            CardType.CHALLENGE -> { return ChallengeCard()}
            }

        return null
        }

}