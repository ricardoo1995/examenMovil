package com.example.jorgecarrillo.examenmoviles

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import java.text.SimpleDateFormat
import java.util.*

class BaseDeDatos {
    companion object BaseDeDatos{
        val BDD_NOMBRE = "Paciente_Medicina"
        val BDD_TABLA_USUARIO_NOMBRE = "paciente"
        val BDD_TABLA_USUARIO_CAMPO_ID = "idPaciente"
        val BDD_TABLA_USUARIO_CAMPO_NOMBRE = "nombre"
        val BDD_TABLA_USUARIO_CAMPO_Apellido = "apellido"
        val BDD_TABLA_USUARIO_CAMPO_FechaNaciento = "fechaNacimiento"
        val BDD_TABLA_USUARIO_CAMPO_Hijos = "hijos"
        val BDD_TABLA_USUARIO_CAMPO_TieneSeguro = "tieneSeguro"

        val BDD_TABLA_USUARIO_NOMBRETABLA = "Medicina"
        val BDD_TABLA_USUARIO_CAMPO_ID_Medicina = "idMedicina"
        val BDD_TABLA_USUARIO_CAMPO_GRAMOSINGERIR = "gramosingerir"
        val BDD_TABLA_USUARIO_CAMPO_NOMBREMEDICINA = "nombreMedicina"
        val BDD_TABLA_USUARIO_CAMPO_COMPOSION = "Composicion"
        val BDD_TABLA_USUARIO_CAMPO_USADOPARA = "usadopara"
        val BDD_TABLA_USUARIO_CAMPO_NUMEROPASTILLAS = "numeroPastillas"
        val BDD_TABLA_USUARIO_CAMPO_PACIENTEID = "pacienteID"


    }

}

class DbHandlerAplicacion(context: Context): SQLiteOpenHelper(context,BaseDeDatos.BDD_NOMBRE,null,1){

    override fun onCreate(db: SQLiteDatabase?) {
        val createTablePaciente = "CREATE TABLE ${BaseDeDatos.BDD_TABLA_USUARIO_NOMBRE} " +
                "(${BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_NOMBRE} VARCHAR(50)," +
                "${BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_Apellido} VARCHAR(50)," +
                "${BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_FechaNaciento} VARCHAR(50)," +
                "${BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_Hijos} INTEGER," +
                "${BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_TieneSeguro} BOOLEAN)"

        val createTableSQLMedicina = "CREATE TABLE ${BaseDeDatos.BDD_TABLA_USUARIO_NOMBRETABLA} " +
                "(${BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_ID_Medicina} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "${BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_GRAMOSINGERIR} DOUBLE," +
                "${BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_NOMBREMEDICINA} VARCHAR(50)," +
                "${BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_COMPOSION} VARCHAR(50)," +
                "${BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_USADOPARA} INTEGER," +
                "${BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_NUMEROPASTILLAS} INTEGER," +
                "${BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_ID} INTEGER)"

        db?.execSQL(createTablePaciente)
        db?.execSQL(createTableSQLMedicina)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    fun insertarPaciente(nombre: String,
                         apellido: String,
                         fechaNacimiento: String,
                         hijos:Int,
                         seguro:Boolean){

        val dbWriteable = writableDatabase
        val cv = ContentValues()

        cv.put(BaseDeDatos.BDD_TABLA_USUARIO_NOMBRE,nombre)
        cv.put(BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_Apellido,apellido)
        cv.put(BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_FechaNaciento,fechaNacimiento)
        cv.put(BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_Hijos,hijos)
        cv.put(BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_TieneSeguro,seguro)

        val resultado = dbWriteable.insert(BaseDeDatos.BDD_TABLA_USUARIO_NOMBRE,null,cv)

        Log.i("database", "Si es -1 hubo error, sino exito: Respuesta: $resultado")

        dbWriteable.close()

    }

    fun insertarMedicina(gramosIngerir: Double,
                         nombreMedicina: String,
                         composion: String,
                         usadoPara: String,
                         numeroPastillas: Int,
                         pacienteId: Int){

        val dbWriteable = writableDatabase
        val cv = ContentValues()

        cv.put(BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_GRAMOSINGERIR,gramosIngerir)
        cv.put(BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_NOMBREMEDICINA,nombreMedicina)
        cv.put(BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_COMPOSION,composion)
        cv.put(BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_USADOPARA,usadoPara)
        cv.put(BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_NUMEROPASTILLAS,numeroPastillas)
        cv.put(BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_PACIENTEID,pacienteId)

        val resultado = dbWriteable.insert(BaseDeDatos.BDD_TABLA_USUARIO_NOMBRETABLA,null,cv)

        Log.i("database", "Si es -1 hubo error, sino exito: Respuesta: $resultado")

        dbWriteable.close()

    }


    fun leerDatosPaciente(){

        val dbReadable = readableDatabase
        val query = "SELECT * FROM ${BaseDeDatos.BDD_TABLA_USUARIO_NOMBRE}"
        val resultado = dbReadable.rawQuery(query,null)

        if (resultado.moveToFirst()) {
            do {
                val nombreActual = resultado.getString(0)
                val hijos = resultado.getString(1).toInt()
                Log.i("database", "El nombre es $nombreActual tiene $hijos")
            } while (resultado.moveToNext())
        }
        resultado.close()
        dbReadable.close()
    }

    fun eliminar(){
        val dbReadable = readableDatabase
        val query = "DELETE ${BaseDeDatos.BDD_TABLA_USUARIO_CAMPO_Apellido} FROM ${BaseDeDatos.BDD_TABLA_USUARIO_NOMBRE}  "
        val resultado = dbReadable.rawQuery(query,null)

        if (resultado.moveToFirst()) {
            do {
                val nombreActual = resultado.getString(0)
                val hijos = resultado.getString(1).toInt()
                Log.i("database", "El nombre es $nombreActual tiene $hijos")
            } while (resultado.moveToNext())
        }
        resultado.close()
        dbReadable.close()
    }


}