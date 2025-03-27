package com.example.producto1

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import controlador.ApuestaController
import database.DatabaseHelper
import modelo.Apuesta
import modelo.Jugador
import kotlin.random.Random

class PlayActivity : AppCompatActivity() {

    private var currentRotation = 0f // Mantiene el ángulo actual de la ruleta
    private lateinit var apuestaController: ApuestaController
    private lateinit var jugador: Jugador // Asumimos que tenemos un jugador ya cargado

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.play_activity)

        // Inicializamos el DatabaseHelper y ApuestaController
        val dbHelper = DatabaseHelper(this)
        apuestaController = ApuestaController(dbHelper)

        // Supongamos que cargamos un jugador de la base de datos
        jugador = Jugador(1, "Juan", 1000) // Ejemplo de jugador

        val roulette: ImageView = findViewById(R.id.roulette)
        val spinButton: Button = findViewById(R.id.button_spin)

        spinButton.setOnClickListener {
            // Aquí podemos realizar la apuesta del jugador antes de girar la ruleta
            val numeroApostado = 7 // Ejemplo de número apostado
            val colorApostado = "Rojo" // Ejemplo de color apostado
            val fichasApostadas = 100 // Ejemplo de fichas apostadas

            // Realizamos la apuesta
            val apuestaRealizada = apuestaController.realizarApuesta(jugador, numeroApostado, colorApostado, fichasApostadas)

            // Comprobamos si la apuesta fue realizada correctamente
            if (apuestaRealizada) {
                spinRoulette(roulette)
            } else {
                Toast.makeText(this, "No tienes suficientes fichas para esta apuesta", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun spinRoulette(rouletteWheel: ImageView) {
        val randomAngle = Random.nextInt(0, 360) // Ángulo aleatorio para el giro
        val totalRotation = 360 * 5 + randomAngle // 5 vueltas completas más el ángulo aleatorio
        currentRotation = (currentRotation + totalRotation) % 360 // Actualiza la rotación acumulada

        rouletteWheel.animate()
            .rotationBy(totalRotation.toFloat())
            .setDuration(3000) // Duración de la animación en ms
            .withEndAction {
                val finalAngle = (360 - currentRotation) % 360
                val sector = calculateSectorWithColor(finalAngle)
                showResult(sector.number, sector.color)
            }
            .start()
    }

    data class RouletteSector(val number: Int, val color: String)

    private fun calculateSectorWithColor(angle: Float): RouletteSector {
        val FACTOR = 360f / 37 / 2 // 4.86 grados
        val sectors = arrayOf(
            RouletteSector(32, "Rojo"), RouletteSector(15, "Negro"), RouletteSector(19, "Rojo"),
            RouletteSector(4, "Negro"), RouletteSector(21, "Rojo"), RouletteSector(2, "Negro"),
            RouletteSector(25, "Rojo"), RouletteSector(17, "Negro"), RouletteSector(34, "Rojo"),
            RouletteSector(6, "Negro"), RouletteSector(27, "Rojo"), RouletteSector(13, "Negro"),
            RouletteSector(36, "Rojo"), RouletteSector(11, "Negro"), RouletteSector(30, "Rojo"),
            RouletteSector(8, "Negro"), RouletteSector(23, "Rojo"), RouletteSector(10, "Negro"),
            RouletteSector(5, "Rojo"), RouletteSector(24, "Negro"), RouletteSector(16, "Rojo"),
            RouletteSector(33, "Negro"), RouletteSector(1, "Rojo"), RouletteSector(20, "Negro"),
            RouletteSector(14, "Rojo"), RouletteSector(31, "Negro"), RouletteSector(9, "Rojo"),
            RouletteSector(22, "Negro"), RouletteSector(18, "Rojo"), RouletteSector(29, "Negro"),
            RouletteSector(7, "Rojo"), RouletteSector(28, "Negro"), RouletteSector(12, "Rojo"),
            RouletteSector(35, "Negro"), RouletteSector(3, "Rojo"), RouletteSector(26, "Negro"),
            RouletteSector(0, "Verde")
        )

        val adjustedAngle = if (angle >= 360 - FACTOR) angle - 360 else angle
        val sectorIndex = ((adjustedAngle + FACTOR) / (FACTOR * 2)).toInt()
        return sectors[sectorIndex]
    }

    private fun showResult(number: Int, color: String) {
        val resultMessage = "La ruleta cayó en el número $number, Color: $color"

        // Verificamos si el jugador ganó
        val apuesta = apuestaController.obtenerApuestasPorJugador(jugador.jugadorId).blockingFirst()
        val apuestaRealizada = apuesta.firstOrNull { it.numeroApostado == number && it.colorApostado == color }

        if (apuestaRealizada != null) {
            // El jugador ganó
            jugador.fichasFinales += apuestaRealizada.fichasApostadas * 2 // Apuesta a color o número
            // DatabaseHelper.updateEntity(jugador) => No sé si hace falta actualizar el Jugador? lo dejo comentado por si acaso
            Toast.makeText(this, "¡Ganaste! Ahora tienes ${jugador.fichasFinales} fichas", Toast.LENGTH_SHORT).show()
        } else {
            // El jugador perdió
            Toast.makeText(this, "¡Perdiste! Ahora tienes ${jugador.fichasFinales} fichas", Toast.LENGTH_SHORT).show()
        }

        AlertDialog.Builder(this)
            .setTitle("Resultado")
            .setMessage(resultMessage)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
