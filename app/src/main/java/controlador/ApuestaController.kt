package controlador

import database.DatabaseHelper
import io.reactivex.rxjava3.core.Flowable
import modelo.Apuesta
import modelo.Jugador

class ApuestaController(private val dbHelper: DatabaseHelper) {

    // Realizar una apuesta
    fun realizarApuesta(jugador: Jugador, numeroApostado: Int, colorApostado: String, fichasApostadas: Int): Boolean {
        val apuesta = Apuesta(
            partidaId = 0,
            jugadorId = jugador.jugadorId,
            numeroApostado = numeroApostado,
            colorApostado = colorApostado,
            fichasApostadas = fichasApostadas,
            fichasIniciales = jugador.fichasFinales,
            fichasFinales = jugador.fichasFinales - fichasApostadas // Resta las fichas apostadas
        )

        // Insertar la apuesta en la base de datos
        dbHelper.insertEntity(apuesta)

        // Actualizar las fichas del jugador después de la apuesta
        jugador.fichasFinales -= fichasApostadas
        dbHelper.updateEntity(jugador)

        // Devolver si la apuesta fue realizada con éxito
        return true
    }

    // Obtener las apuestas realizadas por un jugador
    fun obtenerApuestasPorJugador(jugadorId: Int): Flowable<List<Apuesta>> {
        return dbHelper.getEntityById(jugadorId, Apuesta::class.java) as Flowable<List<Apuesta>>
    }

    // Verificar si el jugador ya apostó por un color
    fun verificarApuestasPorColor(jugadorId: Int, color: String): Flowable<Int> {
        return dbHelper.getApuestasPorColor(jugadorId, color)
    }

    // Verificar si el jugador ya apostó por un número
    fun verificarApuestasPorNumero(jugadorId: Int, numero: Int): Flowable<out Any> {
        return dbHelper.getEntityById(jugadorId, Apuesta::class.java)?.let {
            // Agregar la lógica para verificar si la apuesta ya existe, según sea necesario
            it
        } ?: Flowable.just(0) // Si no existen apuestas, retornar 0
    }

    // Obtener el total de fichas apostadas por un jugador
    fun obtenerTotalFichasApostadas(jugadorId: Int): Flowable<out Any> {
        return dbHelper.getEntityById(jugadorId, Apuesta::class.java)?.let {
            // Realizar la suma de todas las apuestas realizadas por el jugador
            it
        } ?: Flowable.just(0) // Si no existen apuestas, retornar 0
    }
}