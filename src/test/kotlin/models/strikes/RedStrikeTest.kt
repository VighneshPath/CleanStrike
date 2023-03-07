package models.strikes

import models.Coins
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class RedStrikeTest{
    @Test
    fun `should strike and give coin updates for the player`(){
        val strike = RedStrike()
        val expectedCoins = Coins(0, 1)

        val givenCoins = strike.getCoinUpdateForPlayer()

        assertEquals(expectedCoins, givenCoins)
    }

    @Test
    fun `should return the number of points that should be updated for a player after the strike`(){
        val strike = RedStrike()
        val expectedPoints = 3L

        assertEquals(expectedPoints, strike.getPoints())
    }
}