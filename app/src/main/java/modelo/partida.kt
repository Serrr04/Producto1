package modelo

import androidx.lifecycle.LiveData
import androidx.room.*
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@Entity(tableName = "partidas")
data class Partida(
    @PrimaryKey(autoGenerate = true)
    val partidaId: Int = 0,
    val jugadorId: Int,
    val fichasIniciales: Int,
    val fichasFinales: Int,
    val fecha: Long,
    val intentos: Int,

    val dateFormat: SimpleDateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault()),
)

@Dao
interface PartidaDao {
    @Insert
    fun insertPartida(partida: Partida)

    @Query("SELECT * FROM partidas WHERE partidaId = :id")
    fun getPartidaById(id: Int): LiveData<Partida>

    @Query("SELECT * FROM partidas WHERE jugadorId = :jugadorId ORDER BY fecha DESC")
    fun getPartidasByJugador(jugadorId: Int): LiveData<List<Partida>>

    @Query("SELECT SUM(intentos) FROM partidas WHERE jugadorId = :jugadorId")
    fun getTotalIntentos(jugadorId: Int): LiveData<Int>

    @Update
    fun updatePartida(partida: Partida)

    @Delete
    fun deletePartida(partida: Partida)
}

