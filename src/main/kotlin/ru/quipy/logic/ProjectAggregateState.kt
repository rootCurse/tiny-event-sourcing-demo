package ru.quipy.logic

import ru.quipy.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

// Service's business logic
class ProjectAggregateState : AggregateState<UUID, ProjectAggregate> {
    private lateinit var projectId: UUID
    var createdAt: Long = System.currentTimeMillis()
    var updatedAt: Long = System.currentTimeMillis()

    lateinit var projectTitle: String
    lateinit var ownerId: String
    var tasks = mutableSetOf<UUID>()
    var projectStatus = mutableMapOf<UUID, StatusEntity>()
    var users = mutableSetOf<UUID>()

    override fun getId() = projectId
    fun getStatusById(statusId: UUID): StatusEntity? {
        return projectStatus[statusId]
    }

    // State transition functions which is represented by the class member function
    @StateTransitionFunc
    fun projectCreatedApply(event: ProjectCreatedEvent) {
        projectId = event.projectId
        projectTitle = event.title
    }

    @StateTransitionFunc
    fun StatusCreatedApply(event: StatusCreatedEvent) {
        projectStatus[event.statusId] =
            StatusEntity(event.statusId, event.statusColor, event.statusName, event.projectId)
        updatedAt = createdAt
    }

    @StateTransitionFunc
    fun projectUserAddApply(event: ProjectAddUserEvent) {
        projectId = event.projectId
        users.add(event.userId)
        updatedAt = event.createdAt
    }

    fun projectUserDeleteApply(event: ProjectAddUserEvent) {
        users.remove(event.userId)
        updatedAt = event.createdAt
    }

    fun projectUpdatedNameApply(event: ProjectUpdatedNameEvent) {
        projectId = event.projectId
        projectTitle = event.projectName
        updatedAt = event.createdAt
    }

    //@StateTransitionFunc
    //fun taskCreatedApply(event: TaskCreatedEvent) {
    //    tasks[event.taskId] = TaskEntity(event.taskId, event.taskName, mutableSetOf())
    //    updatedAt = createdAt
    //}
}

data class StatusEntity(
    val id: UUID = UUID.randomUUID(),
    val color: StatusColor,
    val statusName: String,
    val projectId: UUID,
)

enum class StatusColor {
    YELLOW,
    GREEN,
    RED,
}
