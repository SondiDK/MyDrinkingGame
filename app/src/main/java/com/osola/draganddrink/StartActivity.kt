package com.osola.draganddrink

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.osola.draganddrink.Fragments.AddPlayerFragment

class StartActivity : AppCompatActivity(), AddPlayerFragment.Listener {


    override fun onButtonClick(playernames: ArrayList<String>) {
      Log.d("ARRAY", playernames.first())
        val mainact = Intent(this, MainActivity::class.java).apply {
            putExtra(PLAYER_NAMES_KEY, playernames)

        }
        startActivity(mainact)
    }

    override fun onAttachFragment(fragment: Fragment) {
        if(fragment is AddPlayerFragment){
            fragment.setOnPlayerAddListener(this)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start2)
    }
}
