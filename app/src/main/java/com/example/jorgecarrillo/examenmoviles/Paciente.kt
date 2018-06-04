/*package com.example.jorgecarrillo.examenmoviles

import android.os.Parcel
import android.os.Parcelable

class Paciente ( var nombrePaciente: String,
                 var apellidos: String,
                 var fechaNacimiento: String,
                 var hijos: Int,

                 var pacienteID: Int) {
    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readInt(),
            parcel.readInt()){

    }

    fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(nombrePaciente)
        parcel.writeString(apellidos)
        parcel.writeString(fechaNacimiento)
        parcel.writeInt(hijos)
        parcel.writeInt(pacienteID)
    }

    fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Paciente> {
        override fun createFromParcel(parcel: Parcel): Paciente {
            return Paciente(parcel)
        }

        override fun newArray(size: Int): Array<Paciente?> {
            return arrayOfNulls(size)
        }
    }
    }*/