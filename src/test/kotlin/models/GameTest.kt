package models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GameTest{
    @Test
    fun `should create a game with two players, and start it`(){
        val player1 = Player()
        val player2 = Player()
        val board = Board(Coins(9, 1))
        val game = Game(board, listOf(player1, player2))
        val option = "1"
        val expectedBoardCoins = Coins(8, 1)
        val expectedPlayerCoins = Coins(1, 0)
        val expectedPlayerPoints = 1L

        game.playTurn(option)

        assertEquals(expectedBoardCoins, board.getCoins())
        assertEquals(expectedPlayerCoins, player1.getCoins())
        assertEquals(expectedPlayerPoints, player1.getPoints())
    }
}