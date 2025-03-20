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

        // Asignar el mismo listener a todos los botones (dentro de onCreate)
        botones.forEach { boton ->
            boton.setOnClickListener { view ->
                when (view.id) {
                    R.id.botonJugar -> SceneManager.loadScene(context, "Ruleta")
                    R.id.botonOpciones -> SceneManager.loadScene(context = Context, "Opciones")
                    R.id.botonSalir -> finish()  // <- Cambié `Exit;` por `finish()` que es la forma correcta de cerrar una actividad
                }
            }
        }
    }
}
