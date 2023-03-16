package models

import exceptions.InvalidOptionTypeException

class Board(private var coins: Coins) {
    fun doesNotHaveCoins() =  !(coins.blackCoins > 0 || coins.redCoins > 0)

    fun updateCoinsBy(coins: Coins): Result<Unit>{
        checkCoinUpdate(coins).onFailure { return Result.failure(it) }

        this.coins.blackCoins += coins.blackCoins
        this.coins.redCoins += coins.redCoins

        return Result.success(Unit)
    }

    fun getCoins() = coins

    private fun checkCoinUpdate(coins: Coins): Result<Unit> {
        if (this.coins.blackCoins + coins.blackCoins < 0 ||
            this.coins.redCoins + coins.redCoins < 0
        ) {
            return Result.failure(InvalidOptionTypeException())
        }
        return Result.success(Unit)
    }
}
