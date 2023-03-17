package models.strikes

import exceptions.InvalidOptionTypeException
import models.StrikeTypes
import models.StrikeTypes.*

object StrikeFactory {
    fun createStrike(chosenStrike: String): Result<Strike> {
        val chosenStrikeType = StrikeTypes.values().firstOrNull{it.option == chosenStrike}
            ?: return Result.failure(InvalidOptionTypeException())
        return when (chosenStrikeType) {
            NORMAL_STRIKE -> Result.success(NormalStrike())
            MULTI_STRIKE -> Result.success(MultiStrike())
            RED_STRIKE -> Result.success(RedStrike())
            STRIKER_STRIKE -> Result.success(StrikerStrike())
            DEFUNCT_STRIKE -> Result.success(DefunctStrike())
            PASS_TURN -> Result.success(PassStrike())
        }
    }
}