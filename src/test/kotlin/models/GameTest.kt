package models

import exceptions.InvalidOptionTypeException
import models.GameStatus.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class GameTest {
    private var player1 = Player()
    private var player2 = Player()
    private var board = Board(Coins(0, 0))
    private var game = Game(board, listOf(player1, player2))

    @BeforeEach
    fun setUp() {
        player1 = Player()
        player2 = Player()
        board = Board(Coins(9, 1))
        game = Game(board, listOf(player1, player2))
    }

    @Test
    fun `should create a game with two players, and player1 plays a Normal Strike`() {
        val option = "1"
        val expectedBoardCoins = Coins(8, 1)
        val expectedPlayerCoins = Coins(1, 0)
        val expectedPlayerPoints = 1L
        val expectedGameStatus = ACTIVE

        val actualGameStatus = game.playTurn(option).getOrNull()

        assertEquals(expectedBoardCoins, board.getCoins())
        assertEquals(expectedPlayerCoins, player1.getPlayerCoins())
        assertEquals(expectedPlayerPoints, player1.getPlayerPoints())
        assertEquals(expectedGameStatus, actualGameStatus)
    }

    @Test
    fun `should create a game with two players, and both players should play a Normal Strike`() {
        val option = "1"
        val expectedBoardCoins = Coins(7, 1)
        val expectedPlayerCoins = Coins(1, 0)
        val expectedPlayerPoints = 1L

        game.playTurn(option)
        game.playTurn(option)

        assertEquals(expectedBoardCoins, board.getCoins())
        assertEquals(expectedPlayerCoins, player2.getPlayerCoins())
        assertEquals(expectedPlayerPoints, player2.getPlayerPoints())
    }

    @Test
    fun `should declare the game as a DRAW if there are no more coins on the board`() {
        board = Board(Coins(0, 0))
        game = Game(board, listOf(player1, player2))
        val expectedGameStatus = DRAW
        val skipTurn = "6"

        val actualBoardStatus = game.playTurn(skipTurn).getOrNull()

        assertEquals(expectedGameStatus, actualBoardStatus)
    }

    @Test
    fun `should complete the game if one player wins`() {
        board = Board(Coins(5, 0))
        game = Game(board, listOf(player1, player2))
        val expectedGameStatus = COMPLETE
        val expectedWinner = player1
        val normalStrike = "1"
        val skipTurn = "6"

        for (turn in 0 until 5) {
            game.playTurn(normalStrike)
            game.playTurn(skipTurn)
        }
        val actualGameStatus = game.playTurn(normalStrike).getOrNull()

        assertEquals(expectedGameStatus, actualGameStatus)
        assertNotNull(game.getWinner())
        assertEquals(expectedWinner, game.getWinner())
    }


    @Test
    fun `should result in a draw`() {
        board = Board(Coins(3, 0))
        game = Game(board, listOf(player1, player2))
        val expectedGameStatus = DRAW
        val normalStrike = "1"

        for (turn in 0 until 5) {
            game.playTurn(normalStrike)
            game.playTurn(normalStrike)
        }
        val actualGameStatus = game.playTurn(normalStrike).getOrNull()

        assertEquals(expectedGameStatus, actualGameStatus)
        assertNull(game.getWinner())
    }

    @Test
    fun `should decrease the points of a player by 1 if they do not get a point for three consecutive turns`() {
        val skipTurn = "6"
        val expectedPlayerPoints = -1L

        for (turn in 0 until 3) {
            game.playTurn(skipTurn)
            game.playTurn(skipTurn)
        }

        assertEquals(expectedPlayerPoints, player1.getPlayerPoints())
    }

    @Test
    fun `should foul 3 times by striker strike to get a 2 additional point loss`() {
        val strikerStrike = "4"
        val normalStrike = "1"
        val expectedPlayer2Points = -5L

        for (turn in 0 until 3) {
            game.playTurn(normalStrike)
            game.playTurn(strikerStrike)
        }

        assertEquals(expectedPlayer2Points, player2.getPlayerPoints())
    }

    @Test
    fun `should foul 3 times by defunct coin to get a 2 additional point loss`() {
        val defunctStrike = "5"
        val normalStrike = "1"
        val expectedPlayer2Points = -8L

        for (turn in 0 until 3) {
            game.playTurn(normalStrike)
            game.playTurn(defunctStrike)
        }

        assertEquals(expectedPlayer2Points, player2.getPlayerPoints())
    }

    @Test
    fun `should throw an error if there are more than one red strikes`() {
        val redStrike = "3"
        val expectedFailureStatus = true

        game.playTurn(redStrike)
        val result = game.playTurn(redStrike)

        assertEquals(expectedFailureStatus, result.isFailure)
        assertThrows<InvalidOptionTypeException> { result.getOrThrow() }
    }

    @Test
    fun `should throw an error if there are more than 4 multi strikes`() {
        val multiStrike = "2"
        val expectedFailureStatus = true

        for (turn in 0 until 4) {
            game.playTurn(multiStrike)
        }
        val result = game.playTurn(multiStrike)

        assertEquals(expectedFailureStatus, result.isFailure)
        assertThrows<InvalidOptionTypeException> { result.getOrThrow() }
    }

    @Test
    fun `should allow more than two players and declare a winner`() {
        val multiStrike = "2"
        val skipTurn = "6"
        val player3     = Player()
        game = Game(board, listOf(player1, player2, player3))

        for (turn in 0..3) {
            game.playTurn(multiStrike)
            game.playTurn(skipTurn)
            game.playTurn(skipTurn)
        }

        assertNotNull(game.getWinner())
        assertEquals(player1, game.getWinner())
    }

    @Test
    fun `should throw an exception if option is invalid`(){
        val invalidStrike = "10"
        val expectedResultFailure = true

        val result = game.playTurn(invalidStrike)

        assertEquals(expectedResultFailure, result.isFailure)
        assertThrows<InvalidOptionTypeException> { result.getOrThrow() }
    }
}