package com.osola.draganddrink

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.card_dialog.*
import kotlinx.android.synthetic.main.card_dialog.view.*
import android.view.WindowManager
import com.osola.draganddrink.Enums.CardType


class DialogCard

constructor(context: Context)  {
 val acontext: Context
    init {
     acontext = context
 }
    fun buildDialog(title: String?, description: String?, type: CardType): Dialog {

        val mDialogView = LayoutInflater.from(acontext).inflate(R.layout.card_dialog, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(acontext).setView(mDialogView)


        //show dialog
        val mAlertDialog = mBuilder.show()
        mAlertDialog.dialogName.text = title
        mAlertDialog.dialogDescription.text = description

        //todo also set bgimage
        when(type){
            CardType.DRINK -> mAlertDialog.dialogName.setBackgroundResource(R.drawable.card_title_style)
            CardType.CHALLENGE -> mAlertDialog.dialogName.setBackgroundResource(R.drawable.card_title_challenge)
            CardType.GAME -> mAlertDialog.dialogName.setBackgroundResource(R.drawable.card_title_style)
        }

       // mAlertDialog.dialogName.setBackgroundResource(R.drawable.card_title_challenge)

        val layoutParams = WindowManager.LayoutParams()
        layoutParams.copyFrom(mAlertDialog.getWindow()?.getAttributes())

        val height = WindowManager.LayoutParams.MATCH_PARENT

        layoutParams.width = 700
        layoutParams.height = height
        mAlertDialog.getWindow()?.setAttributes(layoutParams)
        mAlertDialog.window?.setBackgroundDrawable(null)
        mAlertDialog.setCancelable(false)


        mDialogView.buttonOk.setOnClickListener {
            mAlertDialog.dismiss()

        }
        mDialogView.buttonNo.setOnClickListener {
            mAlertDialog.dismiss()
        }

        return mAlertDialog
    }
}

