package modelo

import androidx.room.*
import io.reactivex.rxjava3.core.Flowable

@Entity(tableName = "jugadores")
data class Jugador(
    @PrimaryKey(autoGenerate = true)
    val jugadorId: Int = 0,
    val nombre: String,
    var fichasFinales: Int = 0
)

@Dao
interface JugadorDao {
    @Insert
    fun insertJugador(jugador: Jugador)

    @Query("SELECT * FROM jugadores WHERE jugadorId = :id")
    fun getJugadorById(id: Int): Flowable<Jugador>

    @Query("SELECT * FROM jugadores ORDER BY fichasFinales DESC")
    fun getAllJugadores(): Flowable<List<Jugador>>

    @Update
    fun updateJugador(jugador: Jugador)

    @Delete
    fun deleteJugador(jugador: Jugador)
}

