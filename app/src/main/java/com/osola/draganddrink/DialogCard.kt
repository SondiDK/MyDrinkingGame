package com.osola.draganddrink

import android.app.AlertDialog
import android.app.Dialog
import android.app.DialogFragment
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import com.osola.draganddrink.R
import kotlinx.android.synthetic.main.login_dialog.*
import kotlinx.android.synthetic.main.login_dialog.view.*

class DialogCard  constructor(context: Context)  {
 val acontext: Context
    init {
     acontext = context
 }
    fun buildDialog(title: String?, description: String?): Dialog {

        val mDialogView = LayoutInflater.from(acontext).inflate(R.layout.login_dialog, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(acontext)
                .setView(mDialogView)

        //show dialog
        val  mAlertDialog = mBuilder.show()
        mAlertDialog.dialogName.text = title
        mAlertDialog.dialogDescription.text = description

        //login button click of custom layout
        mDialogView.dialogLoginBtn.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()

        }
        //cancel button click of custom layout
        mDialogView.dialogCancelBtn.setOnClickListener {
            //dismiss dialog
            mAlertDialog.dismiss()
        }

        return mAlertDialog
    }
}

