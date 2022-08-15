package com.example.infra.db

class PostgreSqlDbConnection: DbConnection {
    override fun sendQuery() {
        println("postgresqlDb")
    }
}