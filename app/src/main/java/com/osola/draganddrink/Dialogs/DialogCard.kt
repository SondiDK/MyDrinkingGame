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
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import com.osola.draganddrink.Model.CardResult


class DialogCard(context: Context, callback: CardListener) {

    private var activityCallback: CardListener = callback
    private val acontext: Context = context

    fun buildDialog(title: String?, description: String?, type: CardType): Dialog {

        val mDialogView = LayoutInflater.from(acontext).inflate(R.layout.card_dialog, null)
        val mBuilder = AlertDialog.Builder(acontext).setView(mDialogView)


        val mAlertDialog = mBuilder.show()
        mAlertDialog.dialogName.text = title
        mAlertDialog.dialogDescription.text = description
        mAlertDialog.setCancelable(false)
        val set = ConstraintSet()
        val layout =  mAlertDialog.buttonContainer
        set.clone(layout)
        when(type){
            CardType.DRINK -> {
                mAlertDialog.dialogName.setBackgroundResource(R.drawable.card_title_style)
                mAlertDialog.cardBackgroundImage.setImageResource(R.drawable.pint)
                //set.connect(mAlertDialog.buttonOk, ConstraintSet)
                //mAlertDialog.buttonContainer.visibility = View.INVISIBLE
            }
            CardType.CHALLENGE -> {
                mAlertDialog.dialogName.setBackgroundResource(R.drawable.card_title_challenge)
                mAlertDialog.cardBackgroundImage.setImageResource(R.drawable.chal_icon)
            }
            CardType.GAME -> {
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
            this.activityCallback.onCardClick( CardResult(true, null))

        }
        mDialogView.buttonNot.setOnClickListener {
            mAlertDialog.dismiss()
            this.activityCallback.onCardClick(CardResult(false, null))
        }

        return mAlertDialog
    }

    interface CardListener {
        fun onCardClick(result: CardResult)
    }


}

