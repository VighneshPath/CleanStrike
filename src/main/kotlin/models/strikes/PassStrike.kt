package models.strikes

import models.Coins

class PassStrike: Strike {
    override fun getPoints() = 0L

    override fun getCoinUpdateForPlayer() = Coins(0, 0)

    override fun getCoinUpdateForBoard() = Coins(0, 0)

    override fun isFoul() = false

    override fun pocketedCoin() = false
}