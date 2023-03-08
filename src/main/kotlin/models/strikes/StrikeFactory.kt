package models.strikes

import exceptions.InvalidOptionTypeException

object StrikeFactory {
    fun createStrike(chosenStrike: String): Strike{
        return when(chosenStrike){
            "1"->NormalStrike()
            "2"->MultiStrike()
            "3"->RedStrike()
            "4"->StrikerStrike()
            "5"->DefunctStrike()
            else->throw InvalidOptionTypeException()
        }
    }
}