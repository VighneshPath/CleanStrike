package models.strikes

import models.Coins

class NormalStrike : Strike {

    override fun getPoints() = 1L

    override fun getCoinUpdateForPlayer() = Coins(1, 0)

    override fun getCoinUpdateForBoard() = Coins(-1, 0)

    override fun isFoul() = false
    override fun pocketedCoin() = true

}