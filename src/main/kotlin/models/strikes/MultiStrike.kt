package models.strikes

import models.Coins

class MultiStrike: Strike {
    override fun getPoints(): Long {
        return 2L
    }

    override fun getCoinUpdateForPlayer(): Coins {
        return Coins(2, 0)
    }

}
