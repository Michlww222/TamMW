package com.android.wasik.data

import android.os.Parcel
import android.os.Parcelable

data class Starship(
    val name: String,
    val model: String,
    val manufacturer: String,
    val costInCredits: String,
    val length: String,
    val maxAtmospheringSpeed: String,
    val crew: String,
    val passengers: String,
    val cargoCapacity: String,
    val consumables: String,
    val hyperdriveRating: String,
    val mglt: String,
    val starshipClass: String,
    // Add other properties as needed
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: "",
        parcel.readString() ?: ""
        // Add other readParcel lines for new properties
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(model)
        parcel.writeString(manufacturer)
        parcel.writeString(costInCredits)
        parcel.writeString(length)
        parcel.writeString(maxAtmospheringSpeed)
        parcel.writeString(crew)
        parcel.writeString(passengers)
        parcel.writeString(cargoCapacity)
        parcel.writeString(consumables)
        parcel.writeString(hyperdriveRating)
        parcel.writeString(mglt)
        parcel.writeString(starshipClass)
        // Add other writeParcel lines for new properties
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Starship> {
        override fun createFromParcel(parcel: Parcel): Starship {
            return Starship(parcel)
        }

        override fun newArray(size: Int): Array<Starship?> {
            return arrayOfNulls(size)
        }
    }
}