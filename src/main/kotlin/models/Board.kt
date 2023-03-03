package models

import exceptions.NoMoreCoinsLeftException
import models.strikes.Strike

class Board(private var coins: Coins) {
    fun playTurn(player: Player, strike: Strike): Player {
        if(!hasCoins()) throw NoMoreCoinsLeftException()
        val updatedPlayer = Player()
        coins = strike.executeStrike(coins)
        updatedPlayer.updatePointsBy(player.getPoints() + strike.getPoints())
        return updatedPlayer
    }

    fun hasCoins(): Boolean {
        return coins.blackCoins > 0 || coins.redCoins > 0
    }

}
