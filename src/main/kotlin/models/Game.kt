package models

import exceptions.NoMoreCoinsLeftException
import models.strikes.StrikeFactory

class Game(private val board: Board, private val playersList: List<Player>) {
    private var currentPlayer = 0

    private fun getCurrentPlayer(): Player{
        return playersList[currentPlayer]
    }

    fun playTurn(option: String) {
        if(!board.hasCoins()) throw NoMoreCoinsLeftException()
        if(option == "6") return

        val strike = StrikeFactory.createStrike(option)

        val boardUpdates = strike.getCoinUpdateForBoard()
        board.updateCoinsBy(boardUpdates)

        val player = getCurrentPlayer()
        val playerCoinsUpdates = strike.getCoinUpdateForPlayer()
        player.updateCoinsBy(playerCoinsUpdates)
        player.updatePointsBy(strike.getPoints())
    }
}