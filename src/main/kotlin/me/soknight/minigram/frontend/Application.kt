package me.soknight.minigram.frontend

import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.http.content.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.server.plugins.statuspages.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.slf4j.LoggerFactory

private val logger = LoggerFactory.getLogger("Application")

fun main(args: Array<String>) = EngineMain.main(args)

val appJson = Json {
    ignoreUnknownKeys = true
    encodeDefaults    = true
    explicitNulls     = false
}

fun Application.module() {
    install(ContentNegotiation) { json(appJson) }
    install(CallLogging)

    install(StatusPages) {
        exception<Throwable> { call, cause ->
            logger.error("Unhandled exception", cause)
            val errorDto = ErrorDto("internal_error", "An unexpected error occurred")
            call.respond(HttpStatusCode.InternalServerError, errorDto)
        }
    }

    routing {
        staticResources("/", "static") {
            default("index.html")
        }
    }
}
