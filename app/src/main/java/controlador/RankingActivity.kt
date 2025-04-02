package controlador

import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import modelo.Jugador
import com.example.producto1.R
import database.DatabaseHelper

class RankingActivity : AppCompatActivity() {
    private lateinit var listView: ListView
    private lateinit var database: SQLiteDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ranking_activity)

        listView = findViewById(R.id.listViewRanking)

        val dbHelper = DatabaseHelper(this) // poner nombre de bbdd para solucionar error
        database = dbHelper.db

        val jugadores = obtenerRanking()
        val adapter = RankingAdapter(this, jugadores)
        listView.adapter = adapter
    }

    private fun obtenerRanking(): List<Jugador> {
        val jugadores = mutableListOf<Jugador>()
        val cursor = try {
            database.rawQuery("SELECT * FROM Jugador ORDER BY fichasFinales DESC LIMIT 5", null)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }

        cursor?.use {
            if (it.moveToFirst()) {
                do {
                    val id = it.getInt(it.getColumnIndexOrThrow("jugadorId"))
                    val nombre = it.getString(it.getColumnIndexOrThrow("nombre"))
                    val fichasFinales = it.getInt(it.getColumnIndexOrThrow("fichasFinales"))
                    jugadores.add(Jugador(id, nombre, fichasFinales))
                } while (it.moveToNext())
            }
        }

        return jugadores
    }


    override fun onDestroy() {
        super.onDestroy()
        database.close()
    }
}