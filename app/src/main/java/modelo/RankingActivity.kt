package modelo

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import modelo.RankingAdapter
import modelo.jugador
import com.example.producto1.R
import modelo.DatabaseHelper // poner nombre y ruta correcta de bbdd para solucionar error

class RankingActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ranking_activity)

        listView = findViewById(R.id.listViewRanking)

        val dbHelper = DatabaseHelper(this) // poner nombre de bbdd para solucionar error
        database = dbHelper.readableDatabase

        val jugadores = obtenerRanking()
        val adapter = RankingAdapter(this, jugadores)
        listView.adapter = adapter
    }

    private fun obtenerRanking(): List<jugador> {
        val jugadores = mutableListOf<jugador>()
        val cursor = database.rawQuery(
            "SELECT * FROM Jugador ORDER BY fichasFinales DESC LIMIT 5",
            null
        )

        if (cursor.moveToFirst()) {
            do {
                val id = cursor.getInt(cursor.getColumnIndexOrThrow("jugadorId"))
                val nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre"))
                val fichasFinales = cursor.getInt(cursor.getColumnIndexOrThrow("fichasFinales"))
                jugadores.add(jugador(id, nombre, fichasFinales))
            } while (cursor.moveToNext())
        }
        cursor.close()
        return jugadores
    }
}