package models.strikes

import models.Coins

class NormalStrike: Strike {

    override fun getPoints(): Long {
        return 1L
    }

    override fun executeStrike(coins: Coins): Coins {
        return Coins(coins.blackCoins-1, coins.redCoins)
    }

}