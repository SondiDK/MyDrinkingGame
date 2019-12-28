package com.osola.draganddrink

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.osola.draganddrink.Enums.CardType
import com.osola.draganddrink.Model.CardDeck
import com.osola.draganddrink.Model.DrinkCard


class CardController():  AppCompatActivity(){

   private val cd = CardDeck()


    fun showCard(context: Context, type: CardType): Int? {
        val dialog = DialogCard(context)
        val card = cd.getCard(type)


        dialog.buildDialog(card?.title, card?.description, type).show()

        // HMm me no likey, alrdy have switch on kinda the same in getcard
        //maybe i can make like a gamemanager class.
        when(card) {
            is DrinkCard ->  return (card.sips)
        }

        return null
    }


}

