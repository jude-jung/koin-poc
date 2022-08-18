package com.example

import com.example.application.DemoAppService
import com.example.di.*
import com.example.presentation.routing.rest.DemoRouting.demoRouting
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.routing.*
import io.ktor.server.testing.*
import org.junit.Rule
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import kotlin.test.Test
import kotlin.test.assertEquals

class ApplicationTest : KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(
            testAppServiceModule,
            testServiceModule,
            testRepositoryModule,
            testDbConnectionModule,
            testConfigModule
        )
    }

    @Test
    fun testRoot() = testApplication {
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