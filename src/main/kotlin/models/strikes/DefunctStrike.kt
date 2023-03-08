package models.strikes

import models.Coins

class DefunctStrike: Strike {
    override fun getPoints(): Long {
        return -2L
    }

    override fun getCoinUpdateForPlayer(): Coins {
        return Coins(0, 0)
    }

    override fun getCoinUpdateForBoard(): Coins{
        return Coins(-1, 0)
    }

    override fun isFoul(): Boolean {
        return true
    }
}