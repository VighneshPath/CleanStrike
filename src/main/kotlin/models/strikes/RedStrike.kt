package models.strikes

import models.Coins

class RedStrike: Strike {
    override fun getPoints(): Long {
        return 3L
    }

    override fun getCoinUpdateForPlayer(): Coins {
        return Coins(0, 1)
    }

    override fun getCoinUpdateForBoard(): Coins {
        return Coins(0, -1)
    }
}