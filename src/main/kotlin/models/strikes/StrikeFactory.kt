package models.strikes

import exceptions.InvalidOptionTypeException
import models.StrikeTypes.*

object StrikeFactory {
    fun createStrike(chosenStrike: String): Result<Strike> {
        return when (chosenStrike) {
            NORMAL_STRIKE.option -> Result.success(NormalStrike())
            MULTI_STRIKE.option -> Result.success(MultiStrike())
            RED_STRIKE.option -> Result.success(RedStrike())
            STRIKER_STRIKE.option -> Result.success(StrikerStrike())
            DEFUNCT_STRIKE.option -> Result.success(DefunctStrike())
            else -> Result.failure(InvalidOptionTypeException())
        }
    }
}