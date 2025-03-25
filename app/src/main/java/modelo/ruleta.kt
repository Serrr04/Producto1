package modelo

class ruleta(
    private var ruletaId: Int,
    private var numero: Int
) {
    // Metodo para asignar el color a los numeros: rojo = impar, negro = par, 0 = verde
    val color: String
        get() = when (numero) {
            0 -> "verde"
            in 1..36 -> if (numero % 2 == 0) "negro" else "rojo"
            else -> "desconocido" // En caso de error
        }


    companion object {
        fun crearTabla(): String {
            val TABLE_RULETA = "Ruleta"
            val COLUMN_RULETA_ID = "apuestaId"
            val COLUMN_NUMERO = "numero"
            val COLUMN_COLOR = "color"

            val CREATE_TABLE_RULETA = """
            CREATE TABLE $TABLE_RULETA (
                $COLUMN_RULETA_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NUMERO INTEGER,
                $COLUMN_COLOR TEXT,
            )
            """

            return CREATE_TABLE_RULETA
        }
    }
}