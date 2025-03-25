package modelo

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.producto1.R
import kotlin.random.Random

class PlayActivity : AppCompatActivity() {
    private var currentRotation = 0f // Mantiene el ángulo actual de la ruleta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.play_activity)

        val roulette: ImageView = findViewById(R.id.roulette)
        val spinButton: Button = findViewById(R.id.button_spin)

        spinButton.setOnClickListener {
            spinRoulette(roulette)
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
        AlertDialog.Builder(this)
            .setTitle("Resultado")
            .setMessage(resultMessage)
            .setPositiveButton("OK") { dialog, _ -> dialog.dismiss() }
            .show()
    }
}
