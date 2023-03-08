package models.strikes

import models.Coins

class StrikerStrike: Strike{
    override fun getPoints(): Long {
        return -1L
    }

    override fun getCoinUpdateForPlayer(): Coins {
        return Coins(0, 0)
    }

    override fun getCoinUpdateForBoard(): Coins {
        return Coins(0, 0)
    }

    override fun isFoul(): Boolean {
        return true
    }
}