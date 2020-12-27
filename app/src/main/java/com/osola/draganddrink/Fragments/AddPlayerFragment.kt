package com.osola.draganddrink.Fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import com.osola.draganddrink.PRELOAD_PLAYERS
import com.osola.draganddrink.R
import kotlinx.android.synthetic.main.fragment_add_player.*
import kotlinx.android.synthetic.main.fragment_add_player.view.*


class AddPlayerFragment : Fragment(), TextWatcher {
    override fun afterTextChanged(p0: Editable?) {

    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
      if(p0?.toString()?.length!! > 0)  {
          this.toggleRemoveOrAddButton(addButton, true)
      }
        else {
          this.toggleRemoveOrAddButton(addButton, false)
      }
    }

    private lateinit var startButton: Button
    private lateinit var removeButton: ImageButton
    private  lateinit var addButton: ImageButton
    private lateinit var numberOfRoundsEt: EditText
    private var activityCallback: Listener?  = null
    private val names = ArrayList<String>()
    private val numberOfRounds: Int = 0;

    interface Listener {
        fun onStartButtonClick(playernames: ArrayList<String>, numberOfRounds: Int)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_add_player, container, false)

        this.startButton = view.startGameBtn
        this.startButton.isEnabled = false
        this.startButton.alpha = 0.1f

        this.removeButton = view.removeBtn
        this.addButton = view.addPlayerBtn
        this.numberOfRoundsEt = view.numberofRoundsEt;

        this.toggleRemoveOrAddButton(removeButton,false)
        this.toggleRemoveOrAddButton(addButton,false)

        view.addPlayerNameText.addTextChangedListener(this)



        view.removeBtn.setOnClickListener {
            this.onRemoveClick()

        }
        view.addPlayerBtn.setOnClickListener{
            this.onAddClick()
        }

        view.startGameBtn.setOnClickListener {
            activityCallback?.onStartButtonClick(names, this.numberOfRoundsEt.text.toString().toInt())
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.openSoftKeyboard(requireContext(), view.addPlayerNameText)
        this.addForFasterTesting()
    }

    private fun onAddClick() {

        val inputName = addPlayerNameText.text.toString()
        this.addPlayerNameToView(inputName)

        this.names.add(inputName)

        if(this.names.size >= 2) {
            this.toggleStartButton()
        }
        this.toggleRemoveOrAddButton(removeButton, true)

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
            this.toggleRemoveOrAddButton(removeButton,false)
        }

        if(this.names.size <= 2) {
            startButton.isEnabled = false
            startButton.alpha = 0.1f
        }
    }

    fun setOnPlayerAddListener(callback: Listener) {
        this.activityCallback = callback

    }

    private fun toggleStartButton() {
        startGameBtn.isEnabled = true
        startGameBtn.alpha = 1f

    }

    private fun toggleRemoveOrAddButton(button: ImageButton, enable: Boolean) {
        if (enable) {
            button.isEnabled = true
            button.alpha = 1f

        } else {
            button.isEnabled = false
            button.alpha = 0.1f
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

    fun openSoftKeyboard(context: Context, view: View) {
        view.requestFocus()
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }

    private fun addForFasterTesting() {
        if (PRELOAD_PLAYERS) {

        val testNames = arrayOf("John", "Erik", "Kim")

        for (name in testNames) {
            this.addPlayerNameToView(name)
            this.names.add(name)
        }

        if(this.names.size >= 2) {
            this.toggleStartButton()
        }

        }

    }


}
