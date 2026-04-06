package me.ashutoshkk.todo.database.model

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document("todos")
data class ToDo(
    val title: String,
    val subText: String?,
    val createdAt: Instant,
    val ownerId: ObjectId,
    @Id val id: ObjectId = ObjectId.get()
)
