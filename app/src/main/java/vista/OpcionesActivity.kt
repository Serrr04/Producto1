package vista

import android.content.Context
import android.media.AudioManager
import android.os.Bundle
import android.provider.Settings
import android.widget.SeekBar
import androidx.appcompat.app.AppCompatActivity

class OpcionesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.opciones)

        val barraBrillo = findViewById<SeekBar>(R.id.barraBrillo)
        val barraVolumen = findViewById<SeekBar>(R.id.barraVolumen)

        // Control del brillo
        barraBrillo.progress = Settings.System.getInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, 125)
        barraBrillo.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Settings.System.putInt(contentResolver, Settings.System.SCREEN_BRIGHTNESS, progress)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        // Control del volumen
        val audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        barraVolumen.progress = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        barraVolumen.max = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        barraVolumen.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0)
            }
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })
    }
}
