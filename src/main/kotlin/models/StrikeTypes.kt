package models

enum class StrikeTypes(val option: String) {
    NORMAL_STRIKE("1"),
    MULTI_STRIKE("2"),
    RED_STRIKE("3"),
    STRIKER_STRIKE("4"),
    DEFUNCT_STRIKE("5"),
    PASS_TURN("6")
}
