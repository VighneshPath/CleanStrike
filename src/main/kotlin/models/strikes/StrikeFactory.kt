package models.strikes

import exceptions.InvalidOptionTypeException
import models.StrikeTypes.*

object StrikeFactory {
    fun createStrike(chosenStrike: String): Strike{
        return when(chosenStrike){
            NORMAL_STRIKE.option ->NormalStrike()
            MULTI_STRIKE.option->MultiStrike()
            RED_STRIKE.option->RedStrike()
            STRIKER_STRIKE.option->StrikerStrike()
            DEFUNCT_STRIKE.option->DefunctStrike()
            else->throw InvalidOptionTypeException()
        }
    }
}