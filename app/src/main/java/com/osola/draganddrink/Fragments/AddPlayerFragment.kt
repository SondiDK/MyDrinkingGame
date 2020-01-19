package com.osola.draganddrink.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import com.osola.draganddrink.R
import kotlinx.android.synthetic.main.fragment_add_player.*
import kotlinx.android.synthetic.main.fragment_add_player.view.*


class AddPlayerFragment : Fragment() {

    private lateinit var startButton: Button
    private lateinit var removeButton: ImageButton
    private var activityCallback: Listener?  = null
    private val names = ArrayList<String>()

    interface Listener {
        fun onButtonClick(playernames: ArrayList<String>)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_player, container, false)

        startButton = view.startGameBtn
        startButton.isEnabled = false
        startButton.alpha = 0.1f

        removeButton = view.removeBtn
        this.toggleRemoveButton(false)

        view.removeBtn.setOnClickListener {
            this.onRemoveClick()

        }
        view.addPlayerBtn.setOnClickListener{
            this.onAddClick()
        }

        view.startGameBtn.setOnClickListener {
            activityCallback?.onButtonClick(names)
        }

        return view
    }


    private fun onAddClick() {

        val inputName = addPlayerNameText.text.toString()
        this.addPlayerNameToView(inputName)

        this.names.add(inputName)

        if(this.names.size >= 2) {
            this.toggleStartButton()
        }
        this.toggleRemoveButton(true)

    }

    private fun onRemoveClick() {

        this.names.removeAt(names.lastIndex)
        this.addedPlayersView.text = ""

       for ( i in names.indices) {

           if (i == names.lastIndex) {
               this.addedPlayersView.append(names[i])
           } else {
               this.addedPlayersView.append(names[i]+ " · ")
           }
        }

        if (names.isEmpty()) {
            this.toggleRemoveButton(false)
        }
    }

    fun setOnPlayerAddListener(callback: Listener) {
        this.activityCallback = callback

    }

    private fun toggleStartButton() {
        startGameBtn.isEnabled = true
        startGameBtn.alpha = 1f

    }

    private fun toggleRemoveButton(enable: Boolean) {
        if (enable) {
            removeButton.isEnabled = true
            removeButton.alpha = 1f

        } else {
            removeButton.isEnabled = false
            removeButton.alpha = 0.1f
        }

    }


    private fun addPlayerNameToView(inputName: String) {
        // append to overview
        if (names.isEmpty()) {
            addedPlayersView.append(inputName)
        } else {
            addedPlayersView.append(" · $inputName")
        }
        addPlayerNameText.text.clear()
    }




}
