package modelo

class jugador (
    private var jugadorId: Int,
    private var nombre: String,
    private var fichasFinales: Int
){
    companion object {
        fun crearTabla(): String {
            val TABLE_JUGADOR = "Jugador"
            val COLUMN_JUGADOR_ID = "jugadorId"
            val COLUMN_NOMBRE = "nombre"
            val COLUMN_FICHAS_FINALES = "fichasFinales"

            val CREATE_TABLE_JUGADOR = """
            CREATE TABLE $TABLE_JUGADOR (
                $COLUMN_JUGADOR_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NOMBRE TEXT,
                $COLUMN_FICHAS_FINALES INTEGER
            )
            """

            return CREATE_TABLE_JUGADOR

        }
    }
}