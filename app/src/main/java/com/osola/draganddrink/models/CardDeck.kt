package com.osola.draganddrink.models

import com.osola.draganddrink.Enums.CardType

class CardDeck {

    fun getCard(type: CardType):Card{
        when (type){
            CardType.DRINK -> { return DrinkCard()}
            CardType.GAME -> { return GameCard() }
            CardType.CHALLENGE -> { return ChallengeCard()}
            }
        }

}