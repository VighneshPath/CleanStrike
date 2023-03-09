package models.strikes

import models.Coins

class DefunctStrike : Strike {
    override fun getPoints() = -2L
    override fun getCoinUpdateForPlayer() = Coins(0, 0)

    override fun getCoinUpdateForBoard() = Coins(-1, 0)
    override fun isFoul() = true
}