package models.strikes

import models.Coins
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NormalStrikeTest{
    @Test
    fun `should strike and give coin updates for the player`(){
        val strike = NormalStrike()
        val expectedCoins = Coins(1, 0)

        val givenCoins = strike.getCoinUpdateForPlayer()

        assertEquals(expectedCoins, givenCoins)
    }

    @Test
    fun `should return the number of points that should be updated for a player after the strike`(){
        val strike = NormalStrike()
        val expectedPoints = 1L

        assertEquals(expectedPoints, strike.getPoints())
    }
}