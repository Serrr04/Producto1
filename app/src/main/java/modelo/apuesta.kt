package modelo

public class apuesta(
    public var apuestaId: Int,
    public var partidaId: Int,
    public var jugadorId: Int,
    public var numeroApostado: Int,
    public var colorApostado: String,
    public var fichasApostadas: Int,
    public var fichasIniciales: Int,
    public var fichasFinales: Int
) {
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

    companion object {
        fun crearTabla(): String {
            val TABLE_APUESTA = "Apuesta"
            val COLUMN_APUESTA_ID = "apuestaId"
            val COLUMN_PARTIDA_ID = "partidaId"
            val COLUMN_JUGADOR_ID = "jugadorId"
            val COLUMN_NUMERO_APOSTADO = "numeroApostado"
            val COLUMN_COLOR_APOSTADO = "colorApostado"
            val COLUMN_FICHAS_APOSTADAS = "fichasApostadas"
            val COLUMN_FICHAS_INICIALES = "fichasIniciales"
            val COLUMN_FICHAS_FINALES = "fichasFinales"

            val CREATE_TABLE_APUESTA = """
            CREATE TABLE $TABLE_APUESTA (
                $COLUMN_APUESTA_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_PARTIDA_ID INTEGER,
                $COLUMN_JUGADOR_ID INTEGER,
                $COLUMN_NUMERO_APOSTADO INTEGER,
                $COLUMN_COLOR_APOSTADO TEXT,
                $COLUMN_FICHAS_APOSTADAS INTEGER,
                $COLUMN_FICHAS_INICIALES INTEGER,
                $COLUMN_FICHAS_FINALES INTEGER
            )
            """

            return CREATE_TABLE_APUESTA;
        }
    }

}
