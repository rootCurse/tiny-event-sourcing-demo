package ru.quipy.api

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import java.util.*

const val TASK_CREATED_EVENT = "TASK_CREATED_EVENT"
const val TASK_CHANGED_EVENT = "TASK_CHANGED_EVENT"
const val TASK_ADD_USER_EVENT = "TASK_ADD_USER_EVENT"
const val TASK_DELETE_USER_EVENT = "TASK_DELETE_USER_EVENT"
const val TASK_CHANGE_STATUS_EVENT = "TASK_CHANGE_STATUS_EVENT"

@DomainEvent(name = TASK_CREATED_EVENT)
class TaskCreatedEvent(
    val taskId: UUID,
    val taskName: String,
    val description: String,
    val statusId: UUID,
    val projectId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<UserAggregate>(
    name = TASK_CREATED_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = TASK_CHANGED_EVENT)
class TaskUpdatedEvent(
    val taskName: String,
    val description: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<UserAggregate>(
    name = TASK_CHANGED_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = TASK_ADD_USER_EVENT)
class TaskAddUserEvent(
    val taskId: UUID,
    val userId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<UserAggregate>(
    name = TASK_ADD_USER_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = TASK_DELETE_USER_EVENT)
class TaskDeleteUserEvent(
    val taskId: UUID,
    val userId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<UserAggregate>(
    name = TASK_DELETE_USER_EVENT,
    createdAt = createdAt
)

@DomainEvent(name = TASK_CHANGE_STATUS_EVENT)
class TaskChangeStatusEvent(
    val taskId: UUID,
    val statusId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<UserAggregate>(
    name = TASK_CHANGE_STATUS_EVENT,
    createdAt = createdAt
)