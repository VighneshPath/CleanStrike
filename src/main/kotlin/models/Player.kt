package models

class Player(private var coins: Coins = Coins(0, 0)){
    private var points = 0L

    fun getPoints(): Long {
        return points
    }

    fun getCoins(): Coins{
        return coins
    }

    fun updatePointsBy(points: Long){
        this.points += points
    }

    fun updateCoinsBy(coins: Coins){
        this.coins.blackCoins += coins.blackCoins
        this.coins.redCoins += coins.redCoins
    }
}
