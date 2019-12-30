package com.osola.draganddrink.Dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.osola.draganddrink.Model.Player
import com.osola.draganddrink.R
import kotlinx.android.synthetic.main.gameover_dialog.view.*


class GameOverDialog(context: Context) {
    private val acontext: Context = context

    fun showDialog(pussyPlayer: Player) {
        val mDialogView = LayoutInflater.from(acontext).inflate(R.layout.gameover_dialog, null)
        //AlertDialogBuilder
        val mBuilder = AlertDialog.Builder(acontext).setView(mDialogView)

        mDialogView.playerPussyTitle.text = "${pussyPlayer.name} is biggest pussy"
        mDialogView.challengesDeniedView.text = "they denied ${pussyPlayer.challengesDenied} challenges"
        val mAlertDialog = mBuilder.show() // or use create
        mAlertDialog.window?.setBackgroundDrawable(null)
    }


}