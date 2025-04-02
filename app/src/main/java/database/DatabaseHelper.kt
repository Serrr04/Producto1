package database

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteCursorDriver
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteQuery
import androidx.room.Database as db
import androidx.room.Room
import androidx.room.RoomDatabase
import io.reactivex.rxjava3.core.Flowable
import modelo.Apuesta
import modelo.ApuestaDao
import modelo.Jugador
import modelo.JugadorDao
import modelo.Ruleta
import modelo.RuletaDao
import modelo.Partida
import modelo.PartidaDao

@db(entities = [Apuesta::class, Jugador::class, Ruleta::class, Partida::class], version = 1)
abstract class Database : RoomDatabase() {
    abstract fun apuestaDao(): ApuestaDao
    abstract fun jugadorDao(): JugadorDao
    abstract fun ruletaDao(): RuletaDao
    abstract fun partidaDao(): PartidaDao

    companion object : SQLiteDatabase.CursorFactory {
        @Volatile
        private var INSTANCE: Database? = null

        fun getDatabase(context: Context): Database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    Database::class.java,
                    "BBDDRuleta"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        override fun newCursor(
            p0: SQLiteDatabase?,
            p1: SQLiteCursorDriver?,
            p2: String?,
            p3: SQLiteQuery?
        ): Cursor {
            TODO("Not yet implemented")
        }
    }
}

class DatabaseHelper(context: Context) {

    val db: SQLiteDatabase = SQLiteDatabase.create(Database)

    // Metodos CRUD
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

    // Metodos adicionales para consultas
    fun getApuestasPorColor(jugadorId: Int, color: String): Flowable<Int> {
        return db.apuestaDao().getApuestasPorColor(jugadorId, color)
    }
}

