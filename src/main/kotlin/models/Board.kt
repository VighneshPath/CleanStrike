package models

class Board(private var coins: Coins) {
    fun hasCoins(): Boolean {
        return coins.blackCoins > 0 || coins.redCoins > 0
    }

    fun updateCoinsBy(coins: Coins){
        this.coins.blackCoins += coins.blackCoins
        this.coins.redCoins += coins.redCoins
    }

    private fun removeFromCoins(coins: Coins){
        this.coins.blackCoins -= coins.blackCoins
        this.coins.redCoins -= coins.redCoins
    }

    fun getCoins() = coins
}
