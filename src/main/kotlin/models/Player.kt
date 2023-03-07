package models

class Player(private var coins: Coins = Coins(0, 0)){
    private var points = 0L

    fun getPoints(): Long {
        return points
    }

    fun updatePointsBy(points: Long){
        this.points += points
    }
}
