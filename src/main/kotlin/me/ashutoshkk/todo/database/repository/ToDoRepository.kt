package me.ashutoshkk.todo.database.repository

import me.ashutoshkk.todo.database.model.ToDo
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ToDoRepository: MongoRepository<ToDo, ObjectId> {
    fun findByOwnerId(ownerId: ObjectId): List<ToDo>
}