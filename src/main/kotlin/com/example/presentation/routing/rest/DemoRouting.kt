package com.example.presentation.routing.rest

import com.example.application.DemoAppService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

object DemoRouting {
    fun Route.demoRouting(demoAppService: DemoAppService) {
        get("/") {
            println("routing")
            call.respondText("Hello World!")
            demoAppService.getDemoResult()
        }
    }
}