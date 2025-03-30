package controlador

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.producto1.R
import controlador.PlayActivity
import controlador.QuitActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paginaprincipal)

        val playButton: Button = findViewById(R.id.botonJugar)
        val optionsButton: Button = findViewById(R.id.botonOpciones)
        val quitButton: Button = findViewById(R.id.botonSalir)

        playButton.setOnClickListener {
            val intent = Intent(this, PlayActivity::class.java)
            startActivity(intent)
        }

        optionsButton.setOnClickListener {
            setContentView(R.layout.opciones)
        }

        quitButton.setOnClickListener {
            val intent = Intent(this, QuitActivity::class.java)
            startActivity(intent)
        }
    }
}
