package com.example.jorgecarrillo.examenmoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_crear_pacient.*

class CrearPacientActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_pacient)
        MainActivity.BaseDeDatos.crearPaciete("Jorge", "Carrillo","09/04/2010",0,true)
        MainActivity.BaseDeDatos.leer()

        boton_guardar.setOnClickListener{view: View ->
            val nombre = edit_nombre.text.toString()
            val apellido = edit_apellido.text.toString()

            MainActivity.BaseDeDatos.crearPaciete("Jorge", "Carrillo","09/04/2010",0,true)

            irAActividadListarOs()
        }
    }

    fun irAActividadListarOs(){
        val intent = Intent(this,ListarPacienteActivity::class.java)
        startActivity(intent)
    }
    }
