package modelo

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.producto1.R

class QuitActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.quit_activity)

        val quitButton: Button = findViewById(R.id.button_quit)
        val backButton: Button = findViewById(R.id.button_back)

        // Acción para salir del juego
        quitButton.setOnClickListener {
            finishAffinity() // Cierra todas las actividades abiertas y sale del juego
        }

        // Acción para volver a la pantalla anterior
        backButton.setOnClickListener {
            finish()
        }
    }
}