package com.example.jorgecarrillo.examenmoviles

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_crear_paciente.*

class CrearMedicinaActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crear_paciente)
        boton_guardar_medicina.setOnClickListener{ view: View -> irAActividadDetalleSo()}
    }

    fun irAActividadDetalleSo(){
        var intent = Intent(this,DetallePacienteActivity::class.java)
        startActivity(intent)
    }
}

