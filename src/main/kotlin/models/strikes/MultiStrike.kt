package models.strikes

import models.Coins

class MultiStrike : Strike {
    override fun getPoints() = 2L
    override fun getCoinUpdateForPlayer() = Coins(2, 0)
    override fun getCoinUpdateForBoard() = Coins(-2, 0)

    override fun isFoul() = false
    override fun pocketedCoin() = true
}
