package com.example

import com.example.application.DemoAppService
import com.example.di.testAppServiceModule
import com.example.di.testDbConnectionModule
import com.example.di.testRepositoryModule
import com.example.di.testServiceModule
import com.example.presentation.routing.rest.DemoRouting.demoRouting
import io.kotest.core.extensions.Extension
import io.kotest.core.spec.style.FreeSpec
import io.kotest.koin.KoinExtension
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.routing.*
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.assertEquals

class ApplicationKoTest : FreeSpec(), KoinTest {
    override fun extensions(): List<Extension> {
        return listOf(
            KoinExtension(
                modules = listOf(
                    testAppServiceModule,
                    testServiceModule,
                    testRepositoryModule,
                    testDbConnectionModule
                )
            )
        )
    }

    init {
        "Application E2E Test" - {
            val demoAppService: DemoAppService by inject()
            val server by lazy {
                embeddedServer(Netty, port = 8080, host = "0.0.0.0") {
                    routing {
                        demoRouting(demoAppService)
                    }
                }
            }
            val client = HttpClient(CIO) {
                defaultRequest {
                    port = 8080
                    host = "0.0.0.0"
                }
            }

            "Hello API Test" {
                server.start(false)
                client.get("/").apply {
                    assertEquals(HttpStatusCode.OK, status)
                    assertEquals("Hello World!", bodyAsText())
                }
            }

            server.stop()
            client.close()
        }
    }
}