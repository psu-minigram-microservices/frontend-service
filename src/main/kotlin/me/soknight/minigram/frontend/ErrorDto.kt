package me.soknight.minigram.frontend

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class ErrorDto(
    val errorCode: String,
    val errorMessage: String,
    val payload: JsonElement? = null
)
