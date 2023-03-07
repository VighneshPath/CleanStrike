package models.strikes

import models.Coins
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class StrikerStrikeTest{
    @Test
    fun `should strike and give coin updates for the player`(){
        val strike = StrikerStrike()
        val expectedCoins = Coins(0, 0)

        val givenCoins = strike.getCoinUpdateForPlayer()

        assertEquals(expectedCoins, givenCoins)
    }

    @Test
    fun `should return the number of points that should be updated for a player after the strike`(){
        val strike = StrikerStrike()
        val expectedPoints = -1L

        assertEquals(expectedPoints, strike.getPoints())
    }
}