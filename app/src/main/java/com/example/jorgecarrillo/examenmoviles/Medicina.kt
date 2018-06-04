/*package com.example.jorgecarrillo.examenmoviles

import android.os.Parcel
import android.os.Parcelable

class Medicina ( var granosAIngerir: Double,
                 var nombre: String,
                 var composicion: String,
                 var usadoPara: String,
                 var fechaCaducidad: String,
                 var numeropastillas: Int,
                 var pacienteID: Int) {
    constructor(parcel: Parcel) : this(
            parcel.readDouble(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt()) {
    }
    fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeDouble(granosAIngerir)
        parcel.writeString(nombre)
        parcel.writeString(composicion)
        parcel.writeString(usadoPara)
        parcel.writeString(fechaCaducidad)
        parcel.writeInt(numeropastillas)
        parcel.writeInt(pacienteID)
    }

    fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Medicina> {
        override fun createFromParcel(parcel: Parcel): Medicina {
            return Medicina(parcel)
        }

        override fun newArray(size: Int): Array<Medicina?> {
            return arrayOfNulls(size)
        }
    }
}
*/