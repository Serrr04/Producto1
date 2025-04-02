package vista
import android.content.Context
import android.content.Intent
import android.widget.Toast
import controlador.OptionsActivity
import controlador.PlayActivity
import controlador.QuitActivity

object SceneManager {

    fun loadScene(context: Context, sceneName: String) {
        val sceneClass = when (sceneName) {
            "Ruleta" -> PlayActivity::class.java
            "Opciones" -> OptionsActivity::class.java
            "Salir" -> QuitActivity::class.java
            else -> null  // Manejo de errores si la escena no existe
        }

        if (sceneClass != null) {
            val intent = Intent(context, sceneClass)
            context.startActivity(intent)
        } else {
            Toast.makeText(context, "Escena no encontrada: $sceneName", Toast.LENGTH_SHORT).show()
        }
    }
}
