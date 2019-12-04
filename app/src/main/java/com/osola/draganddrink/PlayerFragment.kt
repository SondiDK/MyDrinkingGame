package com.osola.draganddrink


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.osola.draganddrink.Model.PlayerManager


class PlayerFragment : Fragment() {

    var activityCallback: PlayerFragment.Listener? = null

    val pm = PlayerManager()

    private lateinit var playerName: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
     val view =  inflater.inflate(R.layout.player_fragment, container, false)

        pm.addPlayer("lars")
        pm.addPlayer("kim")
        pm.addPlayer("ole")
        pm.startGame()

        playerName = view.findViewById(R.id.player_name)

        playerName.text = pm.currentPlayer?.name

        return view
    }


    fun handleSwitchTurn(){
        pm.switchTurn()
        playerName.text = pm.currentPlayer?.name
    }


    interface Listener {
        fun onButtonClick()
    }

}