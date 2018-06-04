package com.example.jorgecarrillo.examenmoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        dbHandler = DbHandlerAplicacion(this)


        boton_listar.setOnClickListener { view: View -> irAActividadListarPaciente() }
        boton_crear.setOnClickListener { view: View -> irAActividadCrearPaciente() }
    }
    companion object BaseDeDatos{
        lateinit var dbHandler: DbHandlerAplicacion

        fun crearPaciete(nombre: String,
                    apellido: String,
                    fechaNacimeinto: String,
                    hijos:Int,
                    afiliado:Boolean)= dbHandler.insertarPaciente(nombre,apellido,fechaNacimeinto,hijos,afiliado)

        fun crearMedicina(cantidadIngerir: Double,
                     nombre: String,
                      composicion: String,
                     usadoPara: String,
                     numeroPastillas: Int,
                     pacienteId: Int) = dbHandler.insertarMedicina(cantidadIngerir,nombre,composicion,usadoPara,numeroPastillas,pacienteId)

        fun leer()= dbHandler.leerDatosPaciente()

    }
    fun irAActividadListarPaciente(){
        val intent = Intent(this,ListarPacienteActivity::class.java)
        startActivity(intent)
    }

    fun irAActividadCrearPaciente(){
        val intent = Intent(this,CrearPacientActivity::class.java)
        startActivity(intent)
    }
}
