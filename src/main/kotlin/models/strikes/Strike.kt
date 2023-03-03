package models.strikes

import models.Coins

interface Strike {
    fun getPoints(): Long
    fun executeStrike(coins: Coins): Coins
}