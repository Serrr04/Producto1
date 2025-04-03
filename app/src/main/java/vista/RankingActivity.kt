package vista

import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.producto1.R
import controlador.RankingAdapter
import database.AppDatabase
import modelo.Jugador

class RankingActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var database: AppDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ranking_activity)

        listView = findViewById(R.id.listViewRanking)

        val dbHelper = AppDatabase // poner nombre de bbdd para solucionar error
        database = dbHelper.getDatabase(this)

        val jugadores = obtenerRanking()
        val adapter = RankingAdapter(this, jugadores)
        listView.adapter = adapter
    }

    private fun obtenerRanking(): List<Jugador> {
        val jugadores = mutableListOf<Jugador>()
        val cursor = database.query(
            "SELECT * FROM Jugador ORDER BY fichasFinales DESC LIMIT 5", null
        )

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("jugadorId"))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                val fichasFinales = cursor.getInt(cursor.getColumnIndexOrThrow("fichasFinales"))
                jugadores.add(Jugador(id, nombre, fichasFinales))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return jugadores
    }
}