package modelo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import modelo.jugador
import com.example.producto1.R

class RankingAdapter(private val context: Context, private val jugadores: List<jugador>) : BaseAdapter() {

    override fun getCount(): Int = jugadores.size

    override fun getItem(position: Int): Any = jugadores[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.item_ranking, parent, false)

        val textNombre = view.findViewById<TextView>(R.id.text_nombre)
        val textPuntos = view.findViewById<TextView>(R.id.text_puntos)

        val jugador = jugadores[position]
        textNombre.text = jugador.nombre
        textPuntos.text = jugador.fichasFinales.toString()

        return view
    }
}