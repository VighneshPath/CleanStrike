package models

import constants.MAX_POSSIBLE_FOULS
import constants.MAX_POSSIBLE_NOT_POCKETED_TURNS_BEFORE_PENALTY
import exceptions.NoMoreCoinsLeftException
import models.GameStatus.*
import models.strikes.Strike
import models.strikes.StrikeFactory

class Game(private val board: Board, private val playersList: List<Player>) {
    private var currentPlayer = -1
    private var winningPlayer: Player? = null
    private var gameStatus = ACTIVE

    private fun getCurrentPlayer(): Player{
        return playersList[currentPlayer]
    }

    private fun updateCurrentPlayer(){
        currentPlayer = (currentPlayer+1)%playersList.size
    }

    private fun checkIfAnyPlayerWon(): Boolean{
        val potentialWinningPlayers = playersList.filter{it.getPlayerPoints() >= 5}
        potentialWinningPlayers.forEach{player1->

            val playersLostTo = playersList.filter{player2->
                (player1 != player2 && (player1.getPlayerPoints() < player2.getPlayerPoints() + 3))
            }

            if(playersLostTo.isEmpty()){
                winningPlayer = player1
                return true
            }
        }
        return false
    }

    fun playTurn(option: String): GameStatus {
        updateCurrentPlayer()
        if(gameStatus == COMPLETE) return gameStatus
        if(!board.hasCoins()){
            if(gameStatus != DRAW) gameStatus = DRAW
            return gameStatus
        }
        if(option == "6"){
            penalizePlayerForNotPocketingCoins()
            return gameStatus
        }

        val strike = StrikeFactory.createStrike(option)

        updatedBoard(strike)
        updatePlayer(strike)

        if(strike.isFoul()){
            penalizePlayerForFoul()
            penalizePlayerForNotPocketingCoins()
        }

        if(checkIfAnyPlayerWon()){
            gameStatus = COMPLETE
        }
        else if(!board.hasCoins()){
            gameStatus = DRAW
        }

        return gameStatus
    }

    private fun penalizePlayerForFoul() {
        val currentPlayer = getCurrentPlayer()
        currentPlayer.addAPenalty()
        if (currentPlayer.getPlayerPenaltyPoints() >= MAX_POSSIBLE_FOULS) {
            currentPlayer.updatePointsBy(-1)
            currentPlayer.resetPenalty()
        }
    }

    private fun penalizePlayerForNotPocketingCoins(){
        val currentPlayer = getCurrentPlayer()
        currentPlayer.addToConsecutiveNotPocketedCoins()
        if (currentPlayer.getPlayerConsecutiveNotPocketedCoins() >= MAX_POSSIBLE_NOT_POCKETED_TURNS_BEFORE_PENALTY) {
            currentPlayer.updatePointsBy(-1)
            currentPlayer.resetConsecutiveNotPocketedCoins()
        }
    }

    private fun updatePlayer(strike: Strike) {
        val player = getCurrentPlayer()
        val playerCoinsUpdates = strike.getCoinUpdateForPlayer()
        player.updateCoinsBy(playerCoinsUpdates)
        player.updatePointsBy(strike.getPoints())
    }

    private fun updatedBoard(strike: Strike) {
        val boardUpdates = strike.getCoinUpdateForBoard()
        board.updateCoinsBy(boardUpdates)
    }

    fun getWinner(): Player?{
        return winningPlayer
    }
}