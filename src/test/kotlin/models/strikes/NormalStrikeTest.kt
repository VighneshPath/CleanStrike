package models.strikes

import models.Board
import models.Player
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class NormalStrikeTest{
    @Test
    fun `should strike a black coin and gain a point`(){
        val strike = NormalStrike()

        val actualStrikeValue = strike.run()

        assertEquals(1L, actualStrikeValue)
    }
}