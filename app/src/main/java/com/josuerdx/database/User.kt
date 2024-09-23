import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class User(
    val id: Int,
    val name: String,
    val email: String,
    val lastName: String // Si también usas este campo
)
