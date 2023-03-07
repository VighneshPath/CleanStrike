package models.strikes

object StrikeFactory {
    fun createStrike(chosenStrike: String): Strike{
        return when(chosenStrike){
            "1"->NormalStrike()
            else->NormalStrike()
        }
    }
}