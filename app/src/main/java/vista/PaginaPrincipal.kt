import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.producto1.R
import controlador.PlayActivity
import controlador.QuitActivity
import modelo.Ruleta
import vista.SceneManager

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
                    R.id.botonJugar -> PlayActivity::class.java
                    R.id.botonOpciones -> SceneManager.loadScene(this, "Opciones")
                    R.id.botonSalir -> QuitActivity::class.java
                }
            }
        }
    }
}
