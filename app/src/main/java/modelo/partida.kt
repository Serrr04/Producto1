package modelo

import androidx.room.*
import io.reactivex.rxjava3.core.Flowable

@Entity(tableName = "partidas")
data class Partida(
    @PrimaryKey(autoGenerate = true)
    val partidaId: Int = 0,
    val jugadorId: Int,
    val fichasIniciales: Int,
    val fichasFinales: Int,
    val fecha: String,  // Cambié a String para simplificar, ya que Room no soporta Date directamente
    val intentos: Int
)

@Dao
interface PartidaDao {
    @Insert
    fun insertPartida(partida: Partida)

    @Query("SELECT * FROM partidas WHERE partidaId = :id")
    fun getPartidaById(id: Int): Flowable<Partida>

    @Update
    fun updatePartida(partida: Partida)

    @Delete
    fun deletePartida(partida: Partida)
}
