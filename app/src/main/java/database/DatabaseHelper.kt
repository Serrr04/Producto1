package database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.reactivex.rxjava3.core.Flowable
import modelo.*

// Definir la base de datos con Room
@Database(entities = [Apuesta::class, Jugador::class, Ruleta::class, Partida::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun apuestaDao(): ApuestaDao
    abstract fun jugadorDao(): JugadorDao
    abstract fun ruletaDao(): RuletaDao
    abstract fun partidaDao(): PartidaDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "BBDDRuleta"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}

// Clase para manejar la base de datos
class DatabaseHelper(context: Context) {

    private val db: AppDatabase = AppDatabase.getDatabase(context)

    // Métodos CRUD
    fun insertEntity(entity: Any) {
        when (entity) {
            is Apuesta -> db.apuestaDao().insertApuesta(entity)
            is Jugador -> db.jugadorDao().insertJugador(entity)
            is Ruleta -> db.ruletaDao().insertRuleta(entity)
            is Partida -> db.partidaDao().insertPartida(entity)
        }
    }

    fun <T> getEntityById(id: Int, entityClass: Class<T>): Any? {
        return when (entityClass) {
            Apuesta::class.java -> db.apuestaDao().getApuestasByJugador(id)
            Jugador::class.java -> db.jugadorDao().getJugadorById(id)
            Ruleta::class.java -> db.ruletaDao().getRuletaById(id)
            Partida::class.java -> db.partidaDao().getPartidaById(id)
            else -> null
        }
    }

    fun updateEntity(entity: Any) {
        when (entity) {
            is Apuesta -> db.apuestaDao().updateApuesta(entity)
            is Jugador -> db.jugadorDao().updateJugador(entity)
            is Ruleta -> db.ruletaDao().updateRuleta(entity)
            is Partida -> db.partidaDao().updatePartida(entity)
        }
    }

    fun deleteEntity(entity: Any) {
        when (entity) {
            is Apuesta -> db.apuestaDao().deleteApuesta(entity)
            is Jugador -> db.jugadorDao().deleteJugador(entity)
            is Ruleta -> db.ruletaDao().deleteRuleta(entity)
            is Partida -> db.partidaDao().deletePartida(entity)
        }
    }

    // Métodos adicionales para consultas
    fun getApuestasPorColor(jugadorId: Int, color: String): Flowable<Int> {
        return db.apuestaDao().getApuestasPorColor(jugadorId, color)
    }
}
