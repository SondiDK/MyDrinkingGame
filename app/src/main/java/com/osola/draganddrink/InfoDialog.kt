package com.osola.draganddrink


import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.osola.draganddrink.Enums.CardType

import com.osola.draganddrink.R
import kotlinx.android.synthetic.main.card_dialog.*
import kotlinx.android.synthetic.main.card_dialog.view.*
import kotlinx.android.synthetic.main.info_dialog.view.*


class InfoDialog(context: Context) {

    val acontext: Context

private lateinit var recyclerView: RecyclerView
private lateinit var viewAdapter: RecyclerView.Adapter<*>
private lateinit var viewManager: RecyclerView.LayoutManager


        init {
            acontext = context
        }

        fun buildDialog(): Dialog {

            val mDialogView = LayoutInflater.from(acontext).inflate(R.layout.info_dialog, null)
            //AlertDialogBuilder
            val mBuilder = AlertDialog.Builder(acontext).setView(mDialogView)

            viewManager = LinearLayoutManager(acontext)

            var drink = DataWrapper("Drink card", R.drawable.card_drink, "You either chose who to drink or drink youself")
            var challenge =  DataWrapper("Challenge card", R.drawable.card_challenge, "Challenge - Do you dare?")
            var game =  DataWrapper("Game card", R.drawable.gamecard, "Fun minigame for everyone")
            var names = arrayListOf<DataWrapper>()
            names.add(drink)
            names.add(challenge)
            names.add(game)


            viewAdapter = MyAdapter(names, acontext)
            recyclerView =  mDialogView.card_info_list



                // use a linear layout manager
            recyclerView.layoutManager = viewManager

                // specify an viewAdapter (see also next example)
            recyclerView.adapter = viewAdapter


            //show dialog
            val mAlertDialog = mBuilder.show()
            mAlertDialog.window?.setBackgroundDrawable(null)

            return mAlertDialog
        }

}
