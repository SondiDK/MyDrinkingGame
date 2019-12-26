package com.osola.draganddrink


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.osola.draganddrink.Model.PlayerManager
import org.w3c.dom.Text


class PlayerFragment : Fragment() {

    var activityCallback: PlayerFragment.Listener? = null

    val pm = PlayerManager()

    private lateinit var currentPlayerNameView: TextView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        // Inflate the layout for this fragment
     val view =  inflater.inflate(R.layout.player_fragment, container, false)
        
        currentPlayerNameView = view.findViewById(R.id.player_name)

        return view
    }


    fun handleSwitchTurn(){
        pm.switchTurn()
        currentPlayerNameView.text = pm.currentPlayer?.name
    }


    fun setPlayernames(names: ArrayList<String>) {
        for (name in names) {
            pm.addPlayer(name);

        }

        pm.startGame()
        currentPlayerNameView.text = pm.currentPlayer?.name
    }


    interface Listener {
        fun onButtonClick()
    }

}