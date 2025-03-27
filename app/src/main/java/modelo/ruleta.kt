package modelo

import androidx.room.*
import io.reactivex.rxjava3.core.Flowable

@Entity(tableName = "ruletas")
data class Ruleta(
    @PrimaryKey(autoGenerate = true)
    val ruletaId: Int = 0,
    val numero: Int
) {
    // Método para asignar el color a los números: rojo = impar, negro = par, 0 = verde
    val color: String
        get() = when (numero) {
            0 -> "verde"
            in 1..36 -> if (numero % 2 == 0) "negro" else "rojo"
            else -> "desconocido" // En caso de error
        }
}

@Dao
interface RuletaDao {
    @Insert
    fun insertRuleta(ruleta: Ruleta)

    @Query("SELECT * FROM ruletas WHERE ruletaId = :id")
    fun getRuletaById(id: Int): Flowable<Ruleta>

    @Update
    fun updateRuleta(ruleta: Ruleta)

    @Delete
    fun deleteRuleta(ruleta: Ruleta)
}
