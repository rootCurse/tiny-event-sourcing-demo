package ru.quipy.logic

import ru.quipy.api.*
//import ru.quipy.api.TaskCreatedEvent
import java.util.*


// Commands : takes something -> returns event
// Here the commands are represented by extension functions, but also can be the class member functions

fun ProjectAggregateState.create(id: UUID, title: String): ProjectCreatedEvent {
    return ProjectCreatedEvent(
        projectId = id,
        title = title,
    )
}

fun ProjectAggregateState.addTask(name: String, description: String, statusId: UUID): TaskCreatedEvent {

    if (this.getStatusById(statusId) == null) {
        throw IllegalArgumentException("Mismatching project ID")
    }

    return TaskCreatedEvent(
        projectId = this.getId(),
        taskId = UUID.randomUUID(),
        taskName = name,
        description = description,
        statusId = statusId
    )
}

fun ProjectAggregateState.createStatus(name: String, color: StatusColor): StatusCreatedEvent {
    if (projectStatus.values.any { it.statusName == name }) {
        throw IllegalArgumentException("Status already exists: $name")
    }
    return StatusCreatedEvent(
        projectId = this.getId(),
        statusId = UUID.randomUUID(),
        statusColor = color,
        statusName = name
    )
}

fun ProjectAggregateState.addExecutor(userId: UUID): ProjectAddUserEvent {
    if (userId in this.users)
        throw IllegalArgumentException("User already exists")
    return ProjectAddUserEvent(projectId = this.getId(), userId = userId);
}

fun ProjectAggregateState.updateName(name: String): ProjectUpdatedNameEvent {
    return ProjectUpdatedNameEvent(projectId = this.getId(), projectName = name);
}

fun ProjectAggregateState.deleteUser(userId: UUID): ProjectDeleteUserEvent {
    return ProjectDeleteUserEvent(projectId = this.getId(), userId = userId);
}

fun ProjectAggregateState.deleteProject(): ProjectDeleteEvent {
    return ProjectDeleteEvent(projectId = this.getId());
}