package models.strikes

import models.Coins

interface Strike {
    fun getPoints(): Long
    fun getCoinUpdateForPlayer(): Coins
    fun getCoinUpdateForBoard(): Coins
}