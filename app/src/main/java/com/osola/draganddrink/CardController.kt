package com.osola.draganddrink

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.osola.draganddrink.Enums.CardType
import com.osola.draganddrink.Model.CardDeck



class CardController():  AppCompatActivity(){

   private val cd = CardDeck()




    fun showCard(context: Context, type: CardType) {
        val dialog = DialogCard(context)
        val card = cd.getCard(type)

        dialog.buildDialog(card?.title, card?.description).show()
    }


}

