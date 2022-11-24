package com.example

import com.example.application.DemoAppService
import com.example.di.*
import com.example.presentation.routing.rest.DemoRouting.demoRouting
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import org.koin.ktor.plugin.Koin

fun main() {
    embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
        install(Koin) {
            modules(
                appServiceModule,
                serviceModule,
                repositoryModule,
                dbConnectionModule,
                configModule
            )
        }

        val demoAppService: DemoAppService by inject()

        routing {
            demoRouting(demoAppService)
        }

    }.start(wait = true)

}
