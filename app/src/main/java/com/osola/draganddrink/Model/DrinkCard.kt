package com.osola.draganddrink.Model

import android.util.Log

private const val MAX_SIPS = 6;
private const val MIN_SIPS = 2

class DrinkCard: Card() {


    override var title: String = "Drink"
    override var description: String = ""
    var sips: Int = 0

    init {
        this.generateSips()
        this.chooseDrinker()
    }

    private fun generateSips() {
        this.sips = (MIN_SIPS..MAX_SIPS).shuffled().first()
    }

    private fun chooseDrinker() {
      val roll = (1..10).shuffled().first()
        Log.d("roll", roll.toString())

        if (roll <= 7) {
           this.description = "You drink " + this.sips + " sips"
        } else {
            this.description = "Pick one to drink " + this.sips + " sips"
        }
    }




}