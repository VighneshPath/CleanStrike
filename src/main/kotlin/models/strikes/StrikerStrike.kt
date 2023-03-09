package models.strikes

import models.Coins

class StrikerStrike : Strike {
    override fun getPoints() = -1L

    override fun getCoinUpdateForPlayer() = Coins(0, 0)

    override fun getCoinUpdateForBoard() = Coins(0, 0)

    override fun isFoul() = true
}