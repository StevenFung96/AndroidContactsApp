package com.example.hexa_stevenfung.androidcontactsapp.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

// Parcelize is Android Extensions plugin now includes an automatic Parcelable implementation generator
@Parcelize
data class Contact(var name: String?, var phone: String?, var image: String?) : Parcelable