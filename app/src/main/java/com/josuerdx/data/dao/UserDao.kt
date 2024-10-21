package com.josuerdx.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.josuerdx.data.model.User

@Dao
interface UserDao {
    @Insert
    suspend fun insertar(usuario: User)

    @Query("SELECT * FROM usuarios WHERE correo = :correo LIMIT 1")
    suspend fun obtenerUsuarioPorCorreo(correo: String): User?

    @Query("SELECT * FROM usuarios WHERE nombre = :nombre LIMIT 1")
    suspend fun obtenerUsuarioPorNombre(nombre: String): User?
}