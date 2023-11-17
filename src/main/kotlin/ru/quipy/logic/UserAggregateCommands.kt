package ru.quipy.logic

import ru.quipy.api.UserCreatedEvent
import java.util.*

fun UserAggregateState.create(id: UUID, userName: String, password: String): UserCreatedEvent {
    return UserCreatedEvent(
        userId = id,
        userName = userName,
        password = password,
    )
}