package com.palfinger.palfingerapi.entities

import kotlin.math.ceil

data class PalchartData(val calulatedGraph: List<ChartPoint>, val currentGraph: List<ChartPoint>?, val maxOutreach: Int, val concentricCircleInterval: Int, val degreeInterval: Float) {
  val numberOfCircles = ceil(maxOutreach / (concentricCircleInterval.toDouble() * 2)).toInt()
}