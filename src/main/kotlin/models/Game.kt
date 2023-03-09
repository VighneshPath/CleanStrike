package models

import constants.INITIAL_PLAYER_INDEX
import constants.MAX_POSSIBLE_FOULS
import constants.MAX_POSSIBLE_NOT_POCKETED_TURNS_BEFORE_PENALTY
import models.GameStatus.*
import models.StrikeTypes.PASS_TURN
import models.strikes.Strike
import models.strikes.StrikeFactory

class Game(private val board: Board, private val playersList: List<Player>) {
    private var currentPlayerIndex = INITIAL_PLAYER_INDEX
    private var winningPlayer: Player? = null
    private var gameStatus = ACTIVE

    fun getWinner() = winningPlayer

    fun playTurn(option: String): GameStatus {
        if (gameStatus != COMPLETE && board.doesNotHaveCoins()) setGameStatusToDrawIfNotAlready()
        if (gameIsAlreadyOver()) return gameStatus

        updateCurrentPlayerIndex()

        if (option == PASS_TURN.option) {
            penalizePlayerForNotPocketingCoins()
            return gameStatus
        }

        val strike = StrikeFactory.createStrike(option)

        updatedBoardBasedOn(strike)
        updatePlayerBasedOn(strike)

        updateGameStatus()

        return gameStatus
    }

    private fun gameIsAlreadyOver() = gameStatus == COMPLETE || gameStatus == DRAW

    private fun setGameStatusToDrawIfNotAlready() {
        if (gameStatus != DRAW) gameStatus = DRAW
    }

    private fun updateCurrentPlayerIndex() {
        currentPlayerIndex = (currentPlayerIndex + 1) % playersList.size
    }

    private fun getCurrentPlayer() = playersList[currentPlayerIndex]

    private fun checkAndSetWinnerIfAnyPlayerWon(): Boolean {
        val potentialWinningPlayers = playersList.filter { it.getPlayerPoints() >= 5 }
        potentialWinningPlayers.forEach { player1 ->

            val playerNotWonWith = playersList.filter { player2 ->
                (player1 != player2 && (player1.getPlayerPoints() <= player2.getPlayerPoints() + 3))
            }

            if (playerNotWonWith.isEmpty()) {
                winningPlayer = player1
                return true
            }
        }
        return false
    }


    private fun updateGameStatus() {
        if (checkAndSetWinnerIfAnyPlayerWon()) {
            gameStatus = COMPLETE
        } else if (board.doesNotHaveCoins()) {
            gameStatus = DRAW
        }
    }

    private fun penalizePlayerForNotPocketingCoins() {
        val currentPlayer = getCurrentPlayer()
        currentPlayer.addToConsecutiveNotPocketedCoins()
        if (currentPlayer.getPlayerConsecutiveNotPocketedCoins() >= MAX_POSSIBLE_NOT_POCKETED_TURNS_BEFORE_PENALTY) {
            currentPlayer.updatePointsBy(-1)
            currentPlayer.resetConsecutiveNotPocketedCoins()
        }
    }

    private fun penalizePlayerForFoul() {
        val currentPlayer = getCurrentPlayer()
        currentPlayer.addAPenalty()
        if (currentPlayer.getPlayerPenaltyPoints() >= MAX_POSSIBLE_FOULS) {
            currentPlayer.updatePointsBy(-1)
            currentPlayer.resetPenalty()
        }
    }


    private fun updatePlayerBasedOn(strike: Strike) {
        val player = getCurrentPlayer()

        player.updateCoinsBy(strike.getCoinUpdateForPlayer())
        player.updatePointsBy(strike.getPoints())

        if (strike.isFoul()) {
            penalizePlayerForNotPocketingCoins()
            penalizePlayerForFoul()
        }
    }

    private fun updatedBoardBasedOn(strike: Strike) = board.updateCoinsBy(strike.getCoinUpdateForBoard())
}