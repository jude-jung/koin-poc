package com.example.infra.db

import io.ktor.server.config.*

class PostgreSqlDbConnection(
    private val port: Int
): DbConnection {
    companion object {
        fun load(config: ApplicationConfig): PostgreSqlDbConnection {
            return PostgreSqlDbConnection(config.propertyOrNull("db.postgresql_port")?.getString()?.toInt() ?: 0)
        }
    }
    override fun sendQuery() {
        println("postgresqlDb port: $port")
    }
}