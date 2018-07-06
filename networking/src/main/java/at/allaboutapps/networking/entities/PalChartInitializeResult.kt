package com.palfinger.palfingerapi.entities

import com.palfinger.palfingerapi.entities.request.SupportPoint
import com.squareup.moshi.Json

data class PalChartInitializeResult(@field:Json(name = "cranename") val craneName: String,
                                    @field:Json(name = "truckname") val truckName: String,
                                    @field:Json(name = "flyjibname") val flyjibName: String,
                                    @field:Json(name = "supportpoints") val supportPoints: List<SupportPoint>)