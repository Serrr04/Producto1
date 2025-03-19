package modelo

class jugador (
    public var jugadorId: Int,
    public var nombre: String,
    public var fichasFinales: Int
){
    // Getters
    fun getJugadorId(): Int = jugadorId

    fun getNombre(): String = nombre

    fun getFichasFinales(): Int = fichasFinales

    // Setters
    fun setJugadorId(jugadorId: Int) {
        this.jugadorId = jugadorId
    }

    fun setNombre(nombre: String) {
        this.nombre = nombre
    }

    fun setFichasFinales(fichasFinales: Int) {
        this.fichasFinales = fichasFinales
    }
}