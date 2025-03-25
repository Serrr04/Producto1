package modelo

import java.util.Date

class partida(
    private var partidaId: Int,
    private var jugadorId: Int,
    private var fichasIniciales: Int,
    private var fichasFinales: Int,
    private var fecha: Date,
    private var intentos: Int
) {
    // Getters
    fun getPartidaId(): Int = partidaId

    fun getJugadorId(): Int = jugadorId

    fun getFichasIniciales(): Int = fichasIniciales

    fun getFichasFinales(): Int = fichasFinales

    fun getFecha(): Date = fecha

    fun getIntentos(): Int = intentos

    // Setters
    fun setPartidaId(partidaId: Int) {
        this.partidaId = partidaId
    }

    fun setJugadorId(jugadorId: Int) {
        this.jugadorId = jugadorId
    }

    fun setFichasIniciales(fichasIniciales: Int) {
        this.fichasIniciales = fichasIniciales
    }

    fun setFichasFinales(fichasFinales: Int) {
        this.fichasFinales = fichasFinales
    }

    fun setFecha(fecha: Date) {
        this.fecha = fecha
    }

    fun setIntentos(intentos: Int) {
        this.intentos = intentos
    }
}