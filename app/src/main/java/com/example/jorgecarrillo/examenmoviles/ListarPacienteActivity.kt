package com.example.jorgecarrillo.examenmoviles

import android.content.Intent
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.PopupMenu
import android.widget.RelativeLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_listar_paciente.*
import java.util.*

class ListarPacienteActivity : AppCompatActivity() {
    var paciente = ArrayList<Paciente1>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_listar_paciente)
        val layoutManager = LinearLayoutManager(this)
        paciente = CrearPaciente.pacientelista
        val adaptador = SistemaOperativoAdaptador(paciente)
        recycler_view.layoutManager = layoutManager
        recycler_view.itemAnimator = DefaultItemAnimator()
        recycler_view.adapter = adaptador
        adaptador.notifyDataSetChanged()

    }
}


class Paciente1(var nombre:String,
                var apellido:Int,
                var fechaNacimiento: Date,
                var hijos:Int,
                var afiliado:Boolean){}

class CrearPaciente(){
    companion object {

        var pacientelista: ArrayList<Paciente1> = ArrayList()

        init {
            pacientelista.add(Paciente1("algo1",1, Date(),1,true))
            pacientelista.add(Paciente1("algo2",2, Date(),0,false))
        }
    }
}

class SistemaOperativoAdaptador(private val listaSistema: List<Paciente1>): RecyclerView.Adapter<SistemaOperativoAdaptador.MyViewHolder>(),
        PopupMenu.OnMenuItemClickListener{

    override fun onMenuItemClick(item: MenuItem): Boolean {
        when (item.getItemId()) {
            R.id.item_menu_aceptar -> {
                Log.i("menu", "Editar")
                return true
            }
            R.id.item_menu_cancelar -> {
                Log.i("menu", "Eliminar")
                return true
            }
            R.id.item_menu_correo -> {
                Log.i("menu", "Correo")
                return true
            }
            else -> {
                Log.i("menu", "Todos los demas")
                return false
            }
        }
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        var nombre: TextView
        var versionApi: TextView
        var pesoEnGigas: TextView
        lateinit var sistema: Paciente1
        var botonDetalle: Button
        var layout: RelativeLayout

        init {
            nombre = view.findViewById(R.id.txtv_nombre) as TextView
            versionApi = view.findViewById(R.id.txtv_detalle1) as TextView
            pesoEnGigas = view.findViewById(R.id.txtv_detalle2) as TextView
            botonDetalle = view.findViewById(R.id.boton_detalle) as Button
            layout = view.findViewById(R.id.relative_layout) as RelativeLayout

            /*layout.setOnClickListener({v ->
                val nombreActual = v.findViewById(R.id.txtv_nombre) as TextView
                val toast = Toast.makeText(v.context, "algo", Toast.LENGTH_LONG)
                toast.show()
            })*/
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_recicler,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val sistema = listaSistema[position]
        holder.nombre.setText(sistema.nombre)
        val versApi = sistema.apellido.toString()
        holder.versionApi.setText(versApi)
        val peso = sistema.hijos.toString()
        holder.pesoEnGigas.setText(peso)
        holder.botonDetalle.setBackgroundColor(Color.GRAY)

        holder.botonDetalle.setOnClickListener{v ->
            val intent = Intent(v.context,DetallePacienteActivity::class.java)
            ContextCompat.startActivity(v.context, intent, null)
        }

        holder.layout.setOnClickListener{view ->
            val popup = PopupMenu(view.context,view)
            popup.setOnMenuItemClickListener(this)
            val inflater = popup.menuInflater
            inflater.inflate(R.menu.pop_up_menu, popup.menu)
            popup.show()
        }


    }

    override fun getItemCount(): Int {
        return listaSistema.size
    }

}