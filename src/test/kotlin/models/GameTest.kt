package models

import models.GameStatus.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GameTest{
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
    fun `should create a game with two players, and player1 plays a Normal Strike`(){
        val option = "1"
        val expectedBoardCoins = Coins(8, 1)
        val expectedPlayerCoins = Coins(1, 0)
        val expectedPlayerPoints = 1L
        val expectedGameStatus = ACTIVE

        val actualGameStatus = game.playTurn(option)

        assertEquals(expectedBoardCoins, board.getCoins())
        assertEquals(expectedPlayerCoins, player1.getPlayerCoins())
        assertEquals(expectedPlayerPoints, player1.getPlayerPoints())
        assertEquals(expectedGameStatus, actualGameStatus)
    }

    @Test
    fun `should create a game with two players, and both players should play a Normal Strike`(){
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
    fun `should declare the game as a DRAW if there are no more coins on the board`(){
        board = Board(Coins(0, 0))
        game = Game(board, listOf(player1, player2))
        val expectedGameStatus = DRAW

        val actualBoardStatus = game.playTurn("6")

        assertEquals(expectedGameStatus, actualBoardStatus)
    }

    @Test
    fun `should complete the game if one player wins`(){
        board = Board(Coins(5, 0))
        game = Game(board, listOf(player1, player2))
        val expectedGameStatus = COMPLETE
        val expectedWinner = player1
        val normalStrike = "1"
        val skipTurn = "6"

        for(turn in 0 until 5){
            game.playTurn(normalStrike)
            game.playTurn(skipTurn)
        }
        val actualGameStatus = game.playTurn(normalStrike)


        assertEquals(expectedGameStatus, actualGameStatus)
        assertNotNull(game.getWinner())
        assertEquals(expectedWinner, game.getWinner())
    }


    @Test
    fun `should result in a draw`(){
        board = Board(Coins(3, 0))
        game = Game(board, listOf(player1, player2))
        val expectedGameStatus = DRAW

        val normalStrike = "1"

        for(turn in 0 until 5){
            game.playTurn(normalStrike)
            game.playTurn(normalStrike)
        }
        val actualGameStatus = game.playTurn(normalStrike)

        assertEquals(expectedGameStatus, actualGameStatus)
        assertNull(game.getWinner())
    }
}