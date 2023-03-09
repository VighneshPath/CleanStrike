package models

import exceptions.InvalidOptionTypeException

class Board(private var coins: Coins) {
    fun doesNotHaveCoins(): Boolean {
        return !(coins.blackCoins > 0 || coins.redCoins > 0)
    }

    fun updateCoinsBy(coins: Coins) {
        checkCoinUpdate(coins)
        this.coins.blackCoins += coins.blackCoins
        this.coins.redCoins += coins.redCoins
    }

    fun getCoins() = coins

    private fun checkCoinUpdate(coins: Coins) {
        if (this.coins.blackCoins + coins.blackCoins < 0 ||
            this.coins.redCoins + coins.redCoins < 0
        ) {
            throw InvalidOptionTypeException()
        }
    }
}
