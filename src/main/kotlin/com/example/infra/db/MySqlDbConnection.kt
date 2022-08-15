package com.example.infra.db

class MySqlDbConnection: DbConnection {
    override fun sendQuery() {
        println("mysqlDb")
    }
}