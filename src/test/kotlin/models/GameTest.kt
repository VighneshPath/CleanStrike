package models

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class GameTest{
    @Test
    fun `should create a game with two players, and start it`(){
        val player1 = Player()
        val player2 = Player()
        val board = Board(Coins(9, 1))

        val game = Game(board)




    }
}