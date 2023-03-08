package models.strikes

import models.Coins

class NormalStrike: Strike {

    override fun getPoints(): Long {
        return 1L
    }

    override fun getCoinUpdateForPlayer(): Coins {
        return Coins(1, 0)
    }

    override fun getCoinUpdateForBoard(): Coins {
        return Coins(-1, 0)
    }

}