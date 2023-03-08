package models.strikes

import models.Coins
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class NormalStrikeTest {
    private var strike = NormalStrike()

    @BeforeEach
    fun setUp() {
        strike = NormalStrike()
    }

    @Test
    fun `should strike and give coin updates for the player`() {
        val expectedCoins = Coins(1, 0)

        val givenCoins = strike.getCoinUpdateForPlayer()

        assertEquals(expectedCoins, givenCoins)
    }

    @Test
    fun `should return the number of points that should be updated for a player after the strike`() {
        val expectedPoints = 1L

        assertEquals(expectedPoints, strike.getPoints())
    }

    @Test
    fun `should not be a foul`() {
        val expectedStatus = false

        assertEquals(expectedStatus, strike.isFoul())
    }
}