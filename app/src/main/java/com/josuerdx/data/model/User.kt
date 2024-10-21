package com.josuerdx.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "usuarios")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val apellido: String,
    val correo: String,
    val direccion: String,
    val contraseña: String // Esta será encriptada
)