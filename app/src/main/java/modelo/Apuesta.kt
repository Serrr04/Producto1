package modelo

import android.content.Context
import androidx.room.*
import androidx.room.Entity
import androidx.room.RoomDatabase
import io.reactivex.rxjava3.core.Flowable

@Entity(tableName = "apuestas")
data class Apuesta(
    @PrimaryKey(autoGenerate = true)
    val apuestaId: Int = 0,
    val partidaId: Int,
    val jugadorId: Int,
    val numeroApostado: Int,
    val colorApostado: String,
    val fichasApostadas: Int,
    val fichasIniciales: Int,
    val fichasFinales: Int
)

@Dao
interface ApuestaDao {
    @Query("SELECT * FROM apuestas WHERE jugadorId = :jugadorId")
    fun getApuestasByJugador(jugadorId: Int): Flowable<List<Apuesta>>

    @Insert
    fun insertApuesta(apuesta: Apuesta)

    @Query("SELECT COUNT(*) FROM apuestas WHERE jugadorId = :jugadorId AND colorApostado = :color")
    fun getApuestasPorColor(jugadorId: Int, color: String): Flowable<Int>

    @Query("SELECT COUNT(*) FROM apuestas WHERE jugadorId = :jugadorId AND numeroApostado = :numero")
    fun getApuestasPorNumero(jugadorId: Int, numero: Int): Flowable<Int>

    @Query("SELECT SUM(fichasApostadas) FROM apuestas WHERE jugadorId = :jugadorId")
    fun getTotalFichasApostadas(jugadorId: Int): Flowable<Int>

    @Update
    fun updateApuesta(apuesta: Apuesta)

    @Delete
    fun deleteApuesta(entity: Apuesta)
}

