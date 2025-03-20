package modelo

import java.util.Date

class partida(
    public var partidaId: Int,
    public var jugadorId: Int,
    public var fichasIniciales: Int,
    public var fichasFinales: Int,
    public var fecha: Date,
    public var intentos: Int
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