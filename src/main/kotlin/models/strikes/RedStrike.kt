package models.strikes

import models.Coins

class RedStrike : Strike {
    override fun getPoints() = 3L

    override fun getCoinUpdateForPlayer() = Coins(0, 1)

    override fun getCoinUpdateForBoard() = Coins(0, -1)

    override fun isFoul() = false
    override fun pocketedCoin() = true
}