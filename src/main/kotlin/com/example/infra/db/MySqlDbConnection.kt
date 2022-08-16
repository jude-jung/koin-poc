package com.example.infra.db

import io.ktor.server.config.*

class MySqlDbConnection(
    private val port: Int
): DbConnection {
    companion object {
        fun load(config: ApplicationConfig): MySqlDbConnection {
            return MySqlDbConnection(config.propertyOrNull("db.mysql_port")?.getString()?.toInt() ?: 0)
        }
    }
    override fun sendQuery() {
        println("mysqlDb port: $port")
    }
}