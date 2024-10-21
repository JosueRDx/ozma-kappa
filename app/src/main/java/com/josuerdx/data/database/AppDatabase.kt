package com.josuerdx.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.josuerdx.data.dao.UserDao
import com.josuerdx.data.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCIA: AppDatabase? = null

        fun obtenerBaseDeDatos(context: Context): AppDatabase {
            return INSTANCIA ?: synchronized(this) {
                val instancia = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "base_datos_app"
                ).build()
                INSTANCIA = instancia
                instancia
            }
        }
    }
}