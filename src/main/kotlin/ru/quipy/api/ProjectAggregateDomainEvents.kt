package ru.quipy.api

import ru.quipy.core.annotations.DomainEvent
import ru.quipy.domain.Event
import ru.quipy.logic.StatusColor
import java.util.*

const val PROJECT_CREATED_EVENT = "PROJECT_CREATED_EVENT"
const val STATUS_CREATED_EVENT = "STATUS_CREATED_EVENT"
const val STATUS_ASSIGNED_TO_TASK_EVENT = "STATUS_ASSIGNED_TO_TASK_EVENT"
//const val TASK_CREATED_EVENT = "TASK_CREATED_EVENT"
const val PROJECT_ADD_USER_EVENT = "PROJECT_ADD_USER_EVENT"
const val PROJECT_DELETE_USER_EVENT = "PROJECT_DELETE_USER_EVENT"
const val PROJECT_UPDATE_NAME_EVENT = "PROJECT_UPDATE_NAME_EVENT"
const val PROJECT_DELETE_EVENT = "PROJECT_DELETE_EVENT"

// API
@DomainEvent(name = PROJECT_CREATED_EVENT)
class ProjectCreatedEvent(
    val projectId: UUID,
    val title: String,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = PROJECT_CREATED_EVENT,
        createdAt = createdAt
)
//TODO
@DomainEvent(name = STATUS_CREATED_EVENT)
class StatusCreatedEvent(
        val projectId: UUID,
        val statusId: UUID,
        val statusName: String,
        val statusColor: StatusColor,
        createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = STATUS_CREATED_EVENT,
        createdAt = createdAt
)

//@DomainEvent(name = TASK_CREATED_EVENT)
//class TaskCreatedEvent(
//    val projectId: UUID,
//    val taskId: UUID,
//    val taskName: String,
//    createdAt: Long = System.currentTimeMillis(),
//) : Event<ProjectAggregate>(
//    name = TASK_CREATED_EVENT,
//    createdAt = createdAt
//)

@DomainEvent(name = STATUS_ASSIGNED_TO_TASK_EVENT)
class StatusAssignedToTaskEvent(
    val projectId: UUID,
    val taskId: UUID,
    val statusId: UUID,
    createdAt: Long = System.currentTimeMillis(),
) : Event<ProjectAggregate>(
    name = STATUS_ASSIGNED_TO_TASK_EVENT,
    createdAt = createdAt
)


@DomainEvent(name = PROJECT_ADD_USER_EVENT)
class ProjectAddUserEvent(
        val projectId: UUID,
        val userId: UUID,
        createdAt: Long = System.currentTimeMillis(),

):Event<ProjectAggregate>(
        name = PROJECT_ADD_USER_EVENT,
        createdAt = createdAt
)

//TODO
@DomainEvent(name = PROJECT_DELETE_USER_EVENT)
class ProjectDeleteUserEvent(
        val projectId: UUID,
        val userId: UUID,
        createdAt: Long = System.currentTimeMillis(),

        ):Event<ProjectAggregate>(
        name = PROJECT_DELETE_USER_EVENT,
        createdAt = createdAt
)


@DomainEvent(name = PROJECT_UPDATE_NAME_EVENT)
class ProjectUpdatedNameEvent(
        val projectId: UUID,
        val projectName: String,
        createdAt: Long = System.currentTimeMillis(),

        ):Event<ProjectAggregate>(
        name = PROJECT_UPDATE_NAME_EVENT,
        createdAt = createdAt
)

//TODO
@DomainEvent(name = PROJECT_DELETE_EVENT)
class ProjectDeleteEvent(
        val projectId: UUID,
        createdAt: Long = System.currentTimeMillis(),

        ):Event<ProjectAggregate>(
        name = PROJECT_DELETE_EVENT,
        createdAt = createdAt
)