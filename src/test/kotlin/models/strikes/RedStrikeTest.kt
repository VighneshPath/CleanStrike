package models.strikes

import models.Coins
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RedStrikeTest{
    private var strike = RedStrike()
    @BeforeEach
    fun setUp(){
        strike = RedStrike()
    }
    @Test
    fun `should strike and give coin updates for the player`(){
        val expectedCoins = Coins(0, 1)

        val givenCoins = strike.getCoinUpdateForPlayer()

        assertEquals(expectedCoins, givenCoins)
    }

    @Test
    fun `should return the number of points that should be updated for a player after the strike`(){
        val expectedPoints = 3L

        assertEquals(expectedPoints, strike.getPoints())
    }

    @Test
    fun `should not be a foul`(){
        val expectedStatus = false

        assertEquals(expectedStatus, strike.isFoul())
    }
}