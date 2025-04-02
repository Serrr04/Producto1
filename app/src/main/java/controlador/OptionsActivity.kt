package controlador

import android.widget.Button
import androidx.core.content.edit
import android.os.Bundle
import android.widget.CheckBox
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.producto1.R

class OptionsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.opciones)

        val brilloSeekBar: SeekBar = findViewById(R.id.barraBrillo)
        val volumenSeekBar: SeekBar = findViewById(R.id.barraVolumen)
        val silenciarCheckBox: CheckBox = findViewById(R.id.silenciar)
        val botonInicio: Button = findViewById(R.id.botonInicio)

        val brilloGuardado = getSharedPreferences("configuraciones", MODE_PRIVATE).getInt("brillo", 50)
        val volumenGuardado = getSharedPreferences("configuraciones", MODE_PRIVATE).getInt("volumen", 50)
        val silenciarGuardado = getSharedPreferences("configuraciones", MODE_PRIVATE).getBoolean("silenciar", false)


        brilloSeekBar.progress = brilloGuardado
        volumenSeekBar.progress = volumenGuardado
        silenciarCheckBox.isChecked = silenciarGuardado

        brilloSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Guardar el valor de brillo
                getSharedPreferences("configuraciones", MODE_PRIVATE).edit {
                    putInt("brillo", progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Lógica para el volumen
        volumenSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Guardar el valor de volumen
                getSharedPreferences("configuraciones", MODE_PRIVATE).edit {
                    putInt("volumen", progress)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Lógica para el checkbox de silenciar
        silenciarCheckBox.setOnCheckedChangeListener { _, isChecked ->
            // Guardar si está silenciado
            getSharedPreferences("configuraciones", MODE_PRIVATE).edit {
                putBoolean("silenciar", isChecked)
            }

            if (isChecked) {
                Toast.makeText(this, "Música silenciada", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "Música activada", Toast.LENGTH_SHORT).show()
            }
        }

        // Lógica para el botón de "Volver a inicio"
        botonInicio.setOnClickListener {
            finish()  // Finaliza la actividad actual y vuelve a la anterior
        }
    }
}
