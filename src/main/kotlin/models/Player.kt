package models

data class Player(private var coins: Coins = Coins(0, 0)){
    private var points = 0L
    private var penaltyPoints = 0L

    fun getPlayerPenaltyPoints(): Long {
        return penaltyPoints
    }

    fun getPlayerPoints(): Long {
        return points
    }

    fun getPlayerCoins(): Coins{
        return coins
    }

    fun updatePointsBy(points: Long){
        this.points += points
    }

    fun addAPenalty(){
        penaltyPoints += 1L
    }

    fun resetPenalty(){
        penaltyPoints = 0L
    }

    fun updateCoinsBy(coins: Coins){
        this.coins.blackCoins += coins.blackCoins
        this.coins.redCoins += coins.redCoins
    }
}
