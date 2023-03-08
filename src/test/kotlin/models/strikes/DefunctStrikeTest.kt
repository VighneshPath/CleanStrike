package models.strikes

import models.Coins
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class DefunctStrikeTest {
    private var strike = DefunctStrike()

    @BeforeEach
    fun setUp() {
        strike = DefunctStrike()
    }

    @Test
    fun `should strike and give coin updates for the player`() {
        val expectedCoins = Coins(0, 0)

        val givenCoins = strike.getCoinUpdateForPlayer()

        assertEquals(expectedCoins, givenCoins)
    }

    @Test
    fun `should return the number of points that should be updated for a player after the strike`() {
        val expectedPoints = -2L

        assertEquals(expectedPoints, strike.getPoints())
    }

    @Test
    fun `should not be a foul`() {
        val expectedStatus = true

        assertEquals(expectedStatus, strike.isFoul())
    }

}