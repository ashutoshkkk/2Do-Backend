package me.ashutoshkk.todo.database.controller

import me.ashutoshkk.todo.database.model.ToDo
import me.ashutoshkk.todo.database.repository.ToDoRepository
import org.bson.types.ObjectId
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.time.Instant

@RestController
@RequestMapping("/todos")
class ToDoController(
    private val repository: ToDoRepository
) {

    data class ToDoRequest(
        val id: String?,
        val title: String,
        val subText: String?,
        val ownerId: String?
    )

    data class ToDoResponse(
        val id: String,
        val title: String,
        val subText: String?,
        val ownerId: String,
        val createdAt: Instant
    )

    @PostMapping
    fun save(body: ToDoRequest): ToDoResponse {
        return repository.save(
            ToDo(
                id = body.id?.let { ObjectId(it) } ?: ObjectId.get(),
                title = body.title,
                subText = body.subText,
                ownerId = ObjectId(body.ownerId),
                createdAt = Instant.now()
            )
        ).toResponse()
    }

    @GetMapping
    fun getByOwnerId(
        @RequestParam(required = true) ownerId: String
    ): List<ToDoResponse> {
        return repository.findByOwnerId(ObjectId(ownerId)).map { it.toResponse() }
    }

}

fun ToDo.toResponse() = ToDoController.ToDoResponse(
    id = id.toHexString(),
    title = title,
    subText = subText,
    ownerId = ownerId.toHexString(),
    createdAt = createdAt
)