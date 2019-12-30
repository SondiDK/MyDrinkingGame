package com.osola.draganddrink.Dialogs

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import kotlinx.android.synthetic.main.card_dialog.*
import kotlinx.android.synthetic.main.card_dialog.view.*
import android.view.WindowManager
import com.osola.draganddrink.Enums.CardType
import com.osola.draganddrink.R


class DialogCard(context: Context, callback: CardListener) {

    private var activityCallback: CardListener = callback
    private val acontext: Context = context

    fun buildDialog(title: String?, description: String?, type: CardType): Dialog {

        val mDialogView = LayoutInflater.from(acontext).inflate(R.layout.card_dialog, null)
        val mBuilder = AlertDialog.Builder(acontext).setView(mDialogView)


        val mAlertDialog = mBuilder.show()
        mAlertDialog.dialogName.text = title
        mAlertDialog.dialogDescription.text = description


        when(type){
            CardType.DRINK -> {
                mAlertDialog.dialogName.setBackgroundResource(R.drawable.card_title_style)
                mAlertDialog.cardBackgroundImage.setImageResource(R.drawable.pint)
                mAlertDialog.buttonContainer.visibility = View.INVISIBLE
            }
            CardType.CHALLENGE -> {
                mAlertDialog.dialogName.setBackgroundResource(R.drawable.card_title_challenge)
                mAlertDialog.cardBackgroundImage.setImageResource(R.drawable.chal_icon)
                mAlertDialog.setCancelable(false)
            }
            CardType.GAME -> {
                mAlertDialog.buttonContainer.visibility = View.INVISIBLE
                mAlertDialog.dialogName.setBackgroundResource(R.drawable.card_title_game)
            }
        }

        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(mAlertDialog.getWindow()?.getAttributes())

        val height = WindowManager.LayoutParams.MATCH_PARENT

        layoutParams.width = 700
        layoutParams.height = height
        mAlertDialog.window?.attributes = layoutParams
        mAlertDialog.window?.setBackgroundDrawable(null)


        mDialogView.buttonOk.setOnClickListener {
            mAlertDialog.dismiss()
            this.activityCallback.onCardClick(true)

        }
        mDialogView.buttonNot.setOnClickListener {
            mAlertDialog.dismiss()
            this.activityCallback.onCardClick(false)
        }

        return mAlertDialog
    }

    interface CardListener {
        fun onCardClick(didIt: Boolean)
    }

}

