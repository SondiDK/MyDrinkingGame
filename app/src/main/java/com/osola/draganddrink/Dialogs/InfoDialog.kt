package com.osola.draganddrink.Dialogs


import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.osola.draganddrink.R

import kotlinx.android.synthetic.main.info_dialog.view.*


class InfoDialog(context: Context) {

    private val acontext: Context = context
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    fun showDialog() {

            val mDialogView = LayoutInflater.from(acontext).inflate(R.layout.info_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(acontext).setView(mDialogView)

            viewManager = LinearLayoutManager(acontext)

            var drink = DataWrapper(
                "Drink card",
                R.drawable.card_drink,
                "Either you get to choose one to drink or you drink youself"
            )
            var challenge = DataWrapper(
                "Challenge card",
                R.drawable.card_challenge,
                "Do you dare to pick a challenge?"
            )
            var game = DataWrapper(
                "Game card",
                R.drawable.gamecard,
                "Fun minigame for everyone"
            )
            var names = arrayListOf<DataWrapper>()
            names.add(drink)
            names.add(challenge)
            names.add(game)


            viewAdapter = InfoDialogAdapter(names, acontext)
            recyclerView =  mDialogView.card_info_list
            recyclerView.overScrollMode = View.OVER_SCROLL_NEVER
            recyclerView.layoutManager = viewManager
            recyclerView.adapter = viewAdapter


            val mAlertDialog = mBuilder.show() // or use create
            mAlertDialog.window?.setBackgroundDrawable(null)

        }

}
