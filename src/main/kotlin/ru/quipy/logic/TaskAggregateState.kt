package ru.quipy.logic

import ru.quipy.api.*
import ru.quipy.core.annotations.StateTransitionFunc
import ru.quipy.domain.AggregateState
import java.util.*

class TaskAggregateState : AggregateState<UUID, TaskAggregate> {
    private lateinit var taskId: UUID
    lateinit var taskName: String
    lateinit var description: String
    lateinit var statusId: UUID
    lateinit var projectId: UUID
    lateinit var userId: UUID

    var createdAt: Long = System.currentTimeMillis()
    var updatedAt: Long = System.currentTimeMillis()

    override fun getId() = taskId

    @StateTransitionFunc
    fun taskCreateApply(event: TaskCreatedEvent) {
        taskId = event.taskId
        taskName = event.taskName
        description = event.description
        statusId = event.statusId
        projectId = event.projectId
        updatedAt = createdAt
    }

    @StateTransitionFunc
    fun taskUpdateApply(event: TaskUpdatedEvent) {
        taskName = event.taskName
        description = event.description
        updatedAt = createdAt
    }

    @StateTransitionFunc
    fun taskAddUserApply(event: TaskAddUserEvent) {
        userId = event.userId
        updatedAt = createdAt
    }

    @StateTransitionFunc
    fun taskChangeStatusApply(event: TaskChangeStatusEvent) {
        statusId = event.statusId
        updatedAt = createdAt
    }
}