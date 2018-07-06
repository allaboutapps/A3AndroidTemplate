package com.palfinger.palfingerapi.entities

import android.os.Parcel
import android.os.Parcelable
import com.squareup.moshi.Json


data class PalcodeEntry(
  val id: String,
  @field:Json(name = "code_number") val codeNumber: String,
  @field:Json(name = "description_short") val descriptionShort: String,
  @field:Json(name = "description_long") val descriptionLong: String,
  val cause: String,
  val solution: String,
  @field:Json(name = "public") val isPublic: Boolean
                       ) : Parcelable {
  constructor(parcel: Parcel) : this(
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readString(),
    parcel.readByte() != 0.toByte()
                                    ) {
  }

  override fun writeToParcel(parcel: Parcel, flags: Int) {
    parcel.writeString(id)
    parcel.writeString(codeNumber)
    parcel.writeString(descriptionShort)
    parcel.writeString(descriptionLong)
    parcel.writeString(cause)
    parcel.writeString(solution)
    parcel.writeByte(if (isPublic) 1 else 0)
  }

  override fun describeContents(): Int {
    return 0
  }

  companion object CREATOR : Parcelable.Creator<PalcodeEntry> {
    override fun createFromParcel(parcel: Parcel): PalcodeEntry {
      return PalcodeEntry(parcel)
    }

    override fun newArray(size: Int): Array<PalcodeEntry?> {
      return arrayOfNulls(size)
    }
  }
}