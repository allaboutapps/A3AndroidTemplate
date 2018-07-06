package com.palfinger.palfingerapi.entities

import com.squareup.moshi.Json
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ProductFeature(val title: String,
                          val subtitle: String?,
                          @field:Json(name = "teaser_text") val description: String,
                          @field:Json(name = "teaser_image") val teaserImage: List<ProductFeatureImage>? = null,
                          @field:Json(name = "affiliatelinks") val moreUrl: String? = null,
                          val videoUrl: String? = null) : Parcelable