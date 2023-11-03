package ru.quipy.logic

import ru.quipy.api.*
import java.util.*

fun TaskAggregateState.create(taskName: String, description: String, statusId: UUID, projectId: UUID): TaskCreatedEvent {
    return TaskCreatedEvent(
            taskId = this.getId(),
            taskName = taskName,
            description = description,
            statusId = statusId,
            projectId = projectId
    )
}

fun TaskAggregateState.update(taskName: String, description: String): TaskUpdatedEvent {
    return TaskUpdatedEvent(
            taskName = taskName,
            description = description,
    )
}

fun TaskAggregateState.addUser( userId: UUID): TaskAddUserEvent {
    return TaskAddUserEvent(
            taskId = this.getId(),
            userId = userId
    )
}

fun TaskAggregateState.deleteUser( userId: UUID): TaskDeleteUserEvent {
    return TaskDeleteUserEvent(
            taskId = this.getId(),
            userId = userId
    )
}

fun TaskAggregateState.changeStatus( statusId: UUID): TaskChangeStatusEvent {
    return TaskChangeStatusEvent(
            taskId = this.getId(),
            statusId = statusId
    )
}