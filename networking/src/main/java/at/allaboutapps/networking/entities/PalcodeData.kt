package com.palfinger.palfingerapi.entities

import com.squareup.moshi.Json

data class PalcodeData(
  @field:Json(name = "data") val entries: List<PalcodeEntry>,
  val count: Int
                      )