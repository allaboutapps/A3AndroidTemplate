package com.palfinger.palfingerapi.entities

import com.squareup.moshi.Json

data class OperatingHour(@field:Json(name = "data") val imageRes: Int,
                         val description: String,
                         val hours: Int)