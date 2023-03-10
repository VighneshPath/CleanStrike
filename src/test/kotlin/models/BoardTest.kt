package models

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class BoardTest {

    @Test
    fun `should create a board with some coins`() {
        val coins = Coins(9, 1)
        val expectedCoinsStatus = true

        val board = Board(coins)

        assertEquals(expectedCoinsStatus, !board.doesNotHaveCoins())
    }


    @Test
    fun `should create a board with exact coins`() {
        val coins = Coins(9, 1)

        val board = Board(coins)

        assertEquals(coins, board.getCoins())
    }

    @Test
    fun `should create a board and remove a black coin`() {
        val coins = Coins(1, 0)
        val board = Board(coins)
        val coinsUpdates = Coins(-1, 0)
        val expectedCoins = Coins(0, 0)

        board.updateCoinsBy(coinsUpdates)

        assertEquals(expectedCoins, board.getCoins())
    }
}
