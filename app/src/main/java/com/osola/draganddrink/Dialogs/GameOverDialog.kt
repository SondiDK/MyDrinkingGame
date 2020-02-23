package com.osola.draganddrink.Dialogs

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import com.osola.draganddrink.Model.Player
import com.osola.draganddrink.R
import kotlinx.android.synthetic.main.gameover_dialog.view.*


class GameOverDialog(context: Context, callback: GameOverListener) {

    private val acontext: Context = context
    private var activityCallback: GameOverListener = callback


    fun showDialog(pussyPlayer: Player) {
        val mDialogView = LayoutInflater.from(acontext).inflate(R.layout.gameover_dialog, null)

        val mBuilder = AlertDialog.Builder(acontext).setView(mDialogView)

        mDialogView.playerPussyTitle.text = "${pussyPlayer.name} is the biggest pussy!\nThey denied ${pussyPlayer.challengesDenied} challenges"
        val mAlertDialog = mBuilder.show() // or use create
        mAlertDialog.window?.setBackgroundDrawable(null)
        mAlertDialog.setCancelable(false)

        mDialogView.playAgainButton.setOnClickListener{
            mAlertDialog.dismiss()
            this.activityCallback.onPlayAgainClick();
        }

        mDialogView.backToMenuButton.setOnClickListener{
            mAlertDialog.dismiss()
            this.activityCallback.onBackToMenuClick()
        }

    }


    interface GameOverListener {
        fun onPlayAgainClick()
        fun onBackToMenuClick()
    }
}

