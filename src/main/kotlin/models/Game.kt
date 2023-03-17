package models

import constants.INITIAL_PLAYER_INDEX
import constants.MAX_POSSIBLE_FOULS
import constants.MAX_POSSIBLE_NOT_POCKETED_TURNS_BEFORE_PENALTY
import models.GameStatus.*
import models.strikes.PassStrike
import models.strikes.Strike
import models.strikes.StrikeFactory

class Game(private val board: Board, private val playersList: List<Player>) {
    private var currentPlayerIndex = INITIAL_PLAYER_INDEX
    private var winningPlayer: Player? = null
    private var gameStatus = ACTIVE

    fun getWinner() = winningPlayer

    fun playTurn(option: String): Result<GameStatus> {
        if (gameStatus != COMPLETE && board.doesNotHaveCoins()) setGameStatusToDrawIfNotAlready()
        if (gameIsAlreadyOver()) return Result.success(gameStatus)

        val strikeResult = StrikeFactory.createStrike(option).onFailure { return Result.failure(it) }

        val strike = strikeResult.getOrDefault(PassStrike())

        updatedBoardBasedOn(strike).onFailure { return Result.failure(it) }
        updatePlayerBasedOn(strike, getNextPlayer())
        updateCurrentPlayerIndex()
        updateGameStatusAndWinner()

        return Result.success(gameStatus)
    }
    private fun updateCurrentPlayerIndex() {
        currentPlayerIndex = (currentPlayerIndex + 1) % playersList.size
    }

    private fun gameIsAlreadyOver() = gameStatus == COMPLETE || gameStatus == DRAW

    private fun setGameStatusToDrawIfNotAlready() {
        if (gameStatus != DRAW) gameStatus = DRAW
    }

    private fun getNextPlayer() = playersList[(currentPlayerIndex + 1) % playersList.size]

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


    private fun updateGameStatusAndWinner() {
        if (checkAndSetWinnerIfAnyPlayerWon()) {
            gameStatus = COMPLETE
        } else if (board.doesNotHaveCoins()) {
            gameStatus = DRAW
        }
    }

    private fun penalizePlayerForNotPocketingCoins(playerToBeUpdated: Player) {
        playerToBeUpdated.addToConsecutiveNotPocketedCoins()
        if (playerToBeUpdated.getPlayerConsecutiveNotPocketedCoins() >= MAX_POSSIBLE_NOT_POCKETED_TURNS_BEFORE_PENALTY) {
            playerToBeUpdated.updatePointsBy(-1)
            playerToBeUpdated.resetConsecutiveNotPocketedCoins()
        }
    }

    private fun penalizePlayerForFoul(playerToBeUpdated: Player) {
        playerToBeUpdated.addAPenalty()
        if (playerToBeUpdated.getPlayerPenaltyPoints() >= MAX_POSSIBLE_FOULS) {
            playerToBeUpdated.updatePointsBy(-1)
            playerToBeUpdated.resetPenalty()
        }
    }

    private fun updatePlayerBasedOn(strike: Strike, playerToBeUpdated: Player) {
        playerToBeUpdated.updateCoinsBy(strike.getCoinUpdateForPlayer())
        playerToBeUpdated.updatePointsBy(strike.getPoints())

        if (strike.isFoul()) {
            penalizePlayerForFoul(playerToBeUpdated)
        }
        if(!strike.pocketedCoin()){
            penalizePlayerForNotPocketingCoins(playerToBeUpdated)
        }
    }

    private fun updatedBoardBasedOn(strike: Strike) = board.updateCoinsBy(strike.getCoinUpdateForBoard())
}