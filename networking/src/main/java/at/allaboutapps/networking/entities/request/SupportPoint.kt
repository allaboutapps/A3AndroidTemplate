package com.palfinger.palfingerapi.entities.request


data class SupportPoint(val number: Int,
                        val type: String,
                        val xvalue: Double,
                        val yvalue: Double,
                        val xvalue2: Double = xvalue,
                        val yvalue2: Double = yvalue)

enum class SupportType(val type: String) {
  WHEEL("Wheel"),
  FRONT_STABILIZER("FrontStabilizer"),
  REAR_STABILIZER("RearStabilizer"),
  STABILIZER_LEFT("CraneStabilizerLeft"),
  STABILIZER_RIGHT("CraneStabilizerRight"),
  ADDITIONAL_STABILIZER("AdditionalStabilizer")
}