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

    // Getters
    fun getRuletaId(): Int = ruletaId

    fun getNumero(): Int = numero

    // Setters
    fun setRuletaId(ruletaId: Int) {
        this.ruletaId = ruletaId
    }

    fun setNumero(numero: Int) {
        this.numero = numero
    }
}