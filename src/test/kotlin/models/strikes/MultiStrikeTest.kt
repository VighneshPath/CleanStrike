package models.strikes

import models.Coins
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class MultiStrikeTest {
    private var strike = MultiStrike()

    @BeforeEach
    fun setUp() {
        strike = MultiStrike()
    }

    @Test
    fun `should strike and give coins which is added to players coins`() {
        val expectedCoins = Coins(2, 0)

        val givenCoins = strike.getCoinUpdateForPlayer()

        assertEquals(expectedCoins, givenCoins)
    }

    @Test
    fun `should return the number of points that should be updated for a player after the strike`() {
        val expectedPoints = 2L

        assertEquals(expectedPoints, strike.getPoints())
    }

    @Test
    fun `should not be a foul`() {
        val expectedStatus = false

        assertEquals(expectedStatus, strike.isFoul())
    }
}