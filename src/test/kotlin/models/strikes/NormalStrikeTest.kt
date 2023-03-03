package models.strikes

import models.Board
import models.Coins
import models.Player
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NormalStrikeTest{
    @Test
    fun `should strike and reduce the number of black coins by 1`(){
        val strike = NormalStrike()
        val coins = Coins(1, 0)
        val expectedCoins = Coins(0, 0)

        val givenCoins = strike.executeStrike(coins)

        assertEquals(expectedCoins, givenCoins)
    }

    @Test
    fun `should return the number of points that should be updated for a player after the strike`(){
        val strike = NormalStrike()
        val expectedPoints = 1L

        assertEquals(expectedPoints, strike.getPoints())
    }
}