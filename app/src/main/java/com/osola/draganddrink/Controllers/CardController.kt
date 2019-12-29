package com.osola.draganddrink.Controllers

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.osola.draganddrink.Dialogs.DialogCard
import com.osola.draganddrink.Enums.CardType
import com.osola.draganddrink.Model.CardDeck


class CardController():  AppCompatActivity(){

   private val cd = CardDeck()


    fun showCard(context: Context, type: CardType, callback: DialogCard.CardListener) {
        val dialog = DialogCard(context, callback)
        val card = cd.getCard(type)

        dialog.buildDialog(card?.title, card?.description, type).show()

    }


}

