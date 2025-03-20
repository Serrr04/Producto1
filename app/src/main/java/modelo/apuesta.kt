package modelo

class apuesta(
    public var apuestaId: Int,
    public var partidaId: Int,
    public var jugadorId: Int,
    public var numeroApostado: Int,
    public var colorApostado: String,
    public var fichasApostadas: Int,
    public var fichasIniciales: Int,
    public var fichasFinales: Int
) {

    // Getters
    fun getApuestaId(): Int = apuestaId

    fun getPartidaId(): Int = partidaId

    fun getJugadorId(): Int = jugadorId

    fun getNumeroApostado(): Int = numeroApostado

    fun getColorApostado(): String = colorApostado

    fun getFichasApostadas(): Int = fichasApostadas

    fun getFichasIniciales(): Int = fichasIniciales

    fun getFichasFinales(): Int = fichasFinales

    // Setters
    fun setApuestaId(apuestaId: Int) {
        this.apuestaId = apuestaId
    }

    fun setPartidaId(partidaId: Int) {
        this.partidaId = partidaId
    }

    fun setJugadorId(jugadorId: Int) {
        this.jugadorId = jugadorId
    }

    fun setNumeroApostado(numeroApostado: Int) {
        this.numeroApostado = numeroApostado
    }

    fun setColorApostado(colorApostado: String) {
        this.colorApostado = colorApostado
    }

    fun setFichasApostadas(fichasApostadas: Int) {
        this.fichasApostadas = fichasApostadas
    }

    fun setFichasIniciales(fichasIniciales: Int) {
        this.fichasIniciales = fichasIniciales
    }

    fun setFichasFinales(fichasFinales: Int) {
        this.fichasFinales = fichasFinales
    }

    fun realizarApuesta() {
        println("Apuesta realizada: $fichasApostadas fichas al número $numeroApostado y color $colorApostado")
    }

    fun calcularResultado(numeroGanador: Int, colorGanador: String) {
        val aciertoNumero = numeroApostado == numeroGanador
        val aciertoColor = colorApostado.equals(colorGanador, ignoreCase = true)

        fichasFinales = when {
            aciertoNumero -> fichasIniciales + (fichasApostadas * 10)    // Acierta número exacto
            aciertoColor -> fichasIniciales + fichasApostadas            // Acierta solo el color
            else -> fichasIniciales - fichasApostadas                    // Pierde la apuesta
        }

        println("Resultado calculado: Fichas finales = $fichasFinales")
    }

}
