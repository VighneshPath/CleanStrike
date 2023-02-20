package models

import models.strikes.Strike

class Board(player1: Player, player2: Player) {
    private var blackCoins = 9L
    private var redCoins = 1L
    fun executeStrike(strike: Strike, player: Player){
        val updateCoins = strike.run()
        player.points+=updateCoins
        blackCoins-=updateCoins
        return
    }
}
