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
import kotlinx.android.synthetic.main.activity_detalle_paciente.*
import java.util.*

class DetallePacienteActivity : AppCompatActivity() {
    var medicina = ArrayList<Medicina>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_paciente)
        val layoutManager = LinearLayoutManager(this)
        medicina = CrearMedicina.medicina
        val adaptador1 = AplicacionAdaptador(medicina)
        recycler_view_app.layoutManager = layoutManager
        recycler_view_app.itemAnimator = DefaultItemAnimator()
        recycler_view_app.adapter = adaptador1
        adaptador1.notifyDataSetChanged()

        boton_paciente_crear.setOnClickListener{ view: View -> irAAtividadCrearMedicina()}
    }

    fun irAAtividadCrearMedicina(){
        var intent = Intent(this,CrearMedicinaActivity::class.java)
        startActivity(intent)
    }
}

class Medicina(var gramosAConsumir: String,
                 var composicion:String,
                 var nombre:String,
                 var usadaPara:String,
                 var fechaCaducidad: Date,
                 var cantidadPastillas:String,
                 var pacienteId:String){}

class CrearMedicina(){

    companion object {

        var medicina: ArrayList<Medicina> = ArrayList()

        init {
            medicina.add(Medicina("1.1","1","algo1","usrl1", Date(),"1.1","1"))
            medicina.add(Medicina("1.2","2","algo2","usrl2", Date(),"2.1","2"))
        }
    }
}

class AplicacionAdaptador(private val listaAplicaciones: List<Medicina>): RecyclerView.Adapter<AplicacionAdaptador.MyViewHolder>(),
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

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view){
        var nombre: TextView
        var cantidadAConsumir: TextView
        var uso: TextView
        lateinit var aplicacion: Medicina
        var botonDetalle: Button
        var layout: RelativeLayout

        init {
            nombre = view.findViewById(R.id.txtv_nombre) as TextView
            cantidadAConsumir = view.findViewById(R.id.txtv_detalle1) as TextView
            uso = view.findViewById(R.id.txtv_detalle2) as TextView
            botonDetalle =view.findViewById(R.id.boton_detalle) as Button
            layout = view.findViewById(R.id.relative_layout) as RelativeLayout
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.activity_recicler,parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val aplicacion = listaAplicaciones[position]
        holder.nombre.setText(aplicacion.nombre)
        holder.cantidadAConsumir.setText(aplicacion.cantidadPastillas)
        holder.uso.setText(aplicacion.usadaPara)
        holder.botonDetalle.setBackgroundColor(Color.GRAY)
        holder.botonDetalle.setOnClickListener{view: View ->
            var intent = Intent(view.context,DetalleMedicinaActivity::class.java)
            ContextCompat.startActivity(view.context, intent, null)

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
        return listaAplicaciones.size
    }
}
