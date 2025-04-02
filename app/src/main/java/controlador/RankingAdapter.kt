package controlador

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import modelo.Jugador
import com.example.producto1.R

class RankingAdapter(private val context: Context, private val jugadores: List<Jugador>) : BaseAdapter() {

    override fun getCount(): Int = jugadores.size

    override fun getItem(position: Int): Any = jugadores[position]

    override fun getItemId(position: Int): Long = position.toLong()

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: ViewHolder
        val view: View

        if (convertView == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_ranking, parent, false)
            viewHolder = ViewHolder(view)
            view.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        val jugador = jugadores[position]
        viewHolder.textNombre.text = jugador.nombre
        viewHolder.textPuntos.text = jugador.fichasFinales.toString()

        return view
    }

    private class ViewHolder(view: View) {
        val textNombre: TextView = view.findViewById(R.id.text_nombre)
        val textPuntos: TextView = view.findViewById(R.id.text_puntos)
    }
}
