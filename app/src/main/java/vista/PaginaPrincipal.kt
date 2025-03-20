import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.producto1.R
import modelo.ruleta
import vista.SceneManager
import vista.vistaOpciones
import vista.vistaRuleta

class PaginaPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.paginaprincipal)

        val botones = listOf(
            findViewById<Button>(R.id.botonJugar),
            findViewById<Button>(R.id.botonOpciones),
            findViewById<Button>(R.id.botonSalir)
        )

        // Cada boton tiene un Listener, dependiendo del id del botón el SceneManager cargará una escena u otra
        botones.forEach { boton ->
            boton.setOnClickListener { view ->
                when (view.id) {
                    R.id.botonJugar -> SceneManager.loadScene(this, "Ruleta")
                    R.id.botonOpciones -> SceneManager.loadScene(this, "Opciones")
                    R.id.botonSalir -> finish()
                }
            }
        }
    }
}
