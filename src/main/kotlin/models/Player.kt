package models

data class Player(private var coins: Coins = Coins(0, 0)) {
    private var points = 0L
    private var penaltyPoints = 0L
    private var consecutiveNotPocketedCoins = 0L

    fun getPlayerPenaltyPoints() = penaltyPoints

    fun getPlayerPoints() = points

    fun getPlayerCoins() = coins

    fun getPlayerConsecutiveNotPocketedCoins() = consecutiveNotPocketedCoins

    fun updatePointsBy(points: Long) {
        this.points += points
    }

    fun addAPenalty() {
        penaltyPoints += 1L
    }

    fun resetPenalty() {
        penaltyPoints = 0L
    }

    fun addToConsecutiveNotPocketedCoins() {
        consecutiveNotPocketedCoins += 1L
    }

    fun resetConsecutiveNotPocketedCoins() {
        consecutiveNotPocketedCoins = 0L
    }

    fun updateCoinsBy(coins: Coins) {
        this.coins.blackCoins += coins.blackCoins
        this.coins.redCoins += coins.redCoins
    }
}
