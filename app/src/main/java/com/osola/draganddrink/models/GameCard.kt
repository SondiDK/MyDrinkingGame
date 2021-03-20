package com.osola.draganddrink.models
class GameCard: Card() {

    override var title: String = "Mini Game"
    override var description: String = ""

    init {
        this.getRandomGame()
    }

    private fun getRandomGame(){
        val ranNum =  (0..CardData.challenges.size-1).shuffled().first()
        this.description = CardData.challenges[ranNum]
    }

        object CardData {
            var challenges = arrayOf(
                "Play a game of Never have I ever - 3 lives",
                "Play a game of 2 truths 1 lie",
                "Play a game of spin the bottle - looser empties their drink ",
                "Play a game of finger on the glass - looser empties their drink"


            )
        }

}
