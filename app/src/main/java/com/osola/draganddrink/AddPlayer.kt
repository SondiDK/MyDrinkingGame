package com.osola.draganddrink

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.fragment_add_player.*
import kotlinx.android.synthetic.main.fragment_add_player.view.*


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [AddPlayer.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [AddPlayer.newInstance] factory method to
 * create an instance of this fragment.
 *
 */
class AddPlayer : Fragment(){
    private lateinit var startButton: Button

    var activityCallback: Listener?  = null
    private val names = ArrayList<String>()

  private fun onAddClick() {
      this.names.add(addPlayerNameText.text.toString())

      addedPlayersView.setText(this.names.toString())
      addPlayerNameText.text.clear()

      if(this.names.size >= 2) {
          this.toggleStartButton()
      }

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

        view.addPlayerBtn.setOnClickListener{
            this.onAddClick()
        }

        view.startGameBtn.setOnClickListener {
            activityCallback?.onButtonClick(names)
        }

        return view
    }


    fun setOnPlayerAddListener(callback: Listener ) {
        this.activityCallback = callback

    }

    private fun toggleStartButton() {
        startGameBtn.isEnabled = true
        startGameBtn.alpha = 1f

    }

    interface Listener {
        fun onButtonClick(playernames: ArrayList<String>)
    }


}
