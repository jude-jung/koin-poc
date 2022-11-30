package com.example

import com.example.application.DemoAppService
import com.example.di.appServiceModule
import com.example.di.configModule
import com.example.di.dbConnectionModule
import com.example.di.repositoryModule
import com.example.di.serviceModule
import com.example.presentation.routing.rest.DemoRouting.demoRouting
import io.ktor.server.application.install
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty
import io.ktor.server.routing.routing
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

fun test() {
    // hotfix 수정
}

fun developTest() {
    // release 수정
    // develop 수정 1
    // release 1.0.8
    // develop commit
}

fun release1_0_6() {
    val stringValue = "Modified Test Value"
}
