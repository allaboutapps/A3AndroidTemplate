package at.allaboutapps.oss_licenses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LicenseScreenSettings(val title: String,
                                 val showUpArrow: Boolean = true) : Parcelable