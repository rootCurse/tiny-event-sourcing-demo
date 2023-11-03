package ru.quipy.controller

import com.fasterxml.jackson.databind.BeanDescription
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import ru.quipy.api.*
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
        return projectEsService.update(projectId) { it.addUser(userId) }
    }
}