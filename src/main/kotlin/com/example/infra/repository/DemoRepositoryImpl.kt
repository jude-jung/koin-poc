package com.example.infra.repository

import com.example.domain.repository.DemoRepository
import com.example.infra.db.DbConnection

class DemoRepositoryImpl(
    private val mySqlDbConnection: DbConnection,
    private val postgreSqlDbConnection: DbConnection
) : DemoRepository {
    override fun getDemo() {
        println("repository")
        mySqlDbConnection.sendQuery()
        postgreSqlDbConnection.sendQuery()
    }
}