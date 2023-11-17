package ru.quipy.controller

import org.springframework.web.bind.annotation.*
import ru.quipy.api.ProjectAddUserEvent
import ru.quipy.api.ProjectAggregate
import ru.quipy.api.ProjectCreatedEvent
import ru.quipy.api.StatusCreatedEvent
import ru.quipy.core.EventSourcingService
import ru.quipy.logic.*
import java.util.*

@RestController
@RequestMapping("/projects")
class ProjectController(
    val projectEsService: EventSourcingService<UUID, ProjectAggregate, ProjectAggregateState>
) {

    @PostMapping("/{projectTitle}")
    fun createProject(@PathVariable projectTitle: String, @RequestParam createdBy: String): ProjectCreatedEvent {
        return projectEsService.create { it.create(UUID.randomUUID(), projectTitle) }
    }

    @PostMapping("/{projectId}/status")
    fun createStatus(
        @PathVariable projectId: UUID,
        @RequestParam color: StatusColor,
        @RequestParam name: String
    ): StatusCreatedEvent {
        return projectEsService.update(projectId) { it.createStatus(name, color) }
    }

    @GetMapping("/{projectId}")
    fun getProject(@PathVariable projectId: UUID): ProjectAggregateState? {
        return projectEsService.getState(projectId)
    }

    @PostMapping("/{projectId}/participants")
    fun addUser(@PathVariable projectId: UUID, @RequestParam userId: UUID): ProjectAddUserEvent {
        return projectEsService.update(projectId) { it.addExecutor(userId) }
    }
}