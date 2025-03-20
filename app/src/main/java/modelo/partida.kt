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

    companion object {
        fun crearTabla(): String {
            val TABLE_PARTIDA = "Partida"
            val COLUMN_PARTIDA_ID = "partidaId"
            val COLUMN_JUGADOR_ID = "jugadorId"
            val COLUMN_FICHAS_INICIALES = "fichasIniciales"
            val COLUMN_FICHAS_FINALES = "fichasFinales"
            val COLUMN_FECHA= "fecha"
            val COLUMN_INTENTOS = "intentos"

            val CREATE_TABLE_PARTIDA = """
            CREATE TABLE $TABLE_PARTIDA (
                $COLUMN_PARTIDA_ID INTEGER,
                $COLUMN_JUGADOR_ID INTEGER,
                $COLUMN_FICHAS_INICIALES INTEGER,
                $COLUMN_FICHAS_FINALES INTEGER,
                $COLUMN_FECHA DATE,
                $COLUMN_INTENTOS INTEGER
            )
            """

            return CREATE_TABLE_PARTIDA;

        }
    }
}