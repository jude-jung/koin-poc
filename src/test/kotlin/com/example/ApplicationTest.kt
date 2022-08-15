package com.example

import com.example.application.DemoAppService
import com.example.di.testAppServiceModule
import com.example.di.testDbConnectionModule
import com.example.di.testRepositoryModule
import com.example.di.testServiceModule
import com.example.presentation.routing.rest.DemoRouting.demoRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import org.koin.core.context.startKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest : KoinTest {
    @Test
    fun testRoot() = testApplication {
        startKoin {
            modules(
                testAppServiceModule,
                testServiceModule,
                testRepositoryModule,
                testDbConnectionModule
            )
        }

        val demoAppService: DemoAppService by inject()

        application {
            routing {
                demoRouting(demoAppService)
            }
        }
        client.get("/").apply {
            assertEquals(HttpStatusCode.OK, status)
            assertEquals("Hello World!", bodyAsText())
        }
    }
}