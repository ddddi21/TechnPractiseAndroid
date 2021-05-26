package com.example.technpractiseandroid.helpers

class FunHelper {

    companion object {
        fun translateAccessibility(degree: Double): String {
            return when (degree) {
                in 0.0..0.3 -> {
                    "so easy to do.."
                }
                in 0.3..0.65 -> {
                    "it probably may have some difficulties"
                }
                in 0.65..1.0 -> {
                    "well, it wouldn't be easy"
                }
                else -> ""
            }
        }

        fun translatePrice(price: Double): String {
            return when (price) {
                0.0 -> "it's free!!"
                in 0.0..0.3 -> {
                    "it's really cheap"
                }
                in 0.3..0.6 -> {
                    "it needs some money"
                }
                in 0.6..1.0 -> {
                    "oh, it's a little expensive"
                }
                else -> ""
            }
        }
    }
}