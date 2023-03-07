package models.strikes

import models.Coins
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MultiStrikeTest{
    @Test
    fun `should strike and give coins which is added to players coins`(){
        val strike = MultiStrike()
        val expectedCoins = Coins(2, 0)

        val givenCoins = strike.getCoinUpdateForPlayer()

        assertEquals(expectedCoins, givenCoins)
    }

    @Test
    fun `should return the number of points that should be updated for a player after the strike`(){
        val strike = MultiStrike()
        val expectedPoints = 2L

        assertEquals(expectedPoints, strike.getPoints())
    }
}