package models

import exceptions.NoMoreCoinsLeftException
import models.strikes.NormalStrike
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

class BoardTest{

    @Test
    fun `should create a board with two players and play a turn for player 1`(){
        val coins = Coins(9, 1)
        val board = Board(coins)
        val player1 = Player()
        val expectedPlayer1Points = player1.getPoints() + 1
        val strike = NormalStrike()

        val updatedPlayer1 = board.playTurn(player1, strike)

        assertEquals(expectedPlayer1Points, updatedPlayer1.getPoints())
    }

    @Test
    fun `should create a board with two players and the game should finish once there are 0 coins on the board`(){
        val coins = Coins(1, 0)
        val board = Board(coins)
        val player1 = Player()
        val strike = NormalStrike()
        val expectedBoardStatus = false

        board.playTurn(player1, strike)

        assertEquals(expectedBoardStatus, board.hasCoins())
        assertThrows<NoMoreCoinsLeftException>{board.playTurn(player1, strike)}
    }
}
