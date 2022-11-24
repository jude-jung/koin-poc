package com.example.di

import com.example.application.DemoAppService
import com.example.application.DemoAppServiceImpl
import com.example.domain.repository.DemoRepository
import com.example.domain.service.DemoService
import com.example.domain.service.DemoServiceImpl
import com.example.infra.db.DbConnection
import com.example.infra.db.MySqlDbConnection
import com.example.infra.db.PostgreSqlDbConnection
import com.example.infra.repository.DemoRepositoryImpl
import com.typesafe.config.ConfigFactory
import io.ktor.server.config.*
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.named
import org.koin.core.module.dsl.singleOf
import org.koin.core.module.dsl.withOptions
import org.koin.core.qualifier.named
import org.koin.dsl.bind
import org.koin.dsl.module

private const val mySqlDbConnection = "mysql"
private const val postgresqlDbConnection = "postgresql"

val testAppServiceModule = module {
    singleOf(::DemoAppServiceImpl) bind DemoAppService::class
}

val testServiceModule = module {
    singleOf(::DemoServiceImpl) bind DemoService::class
}

val testRepositoryModule = module {
    single { DemoRepositoryImpl(get(named(mySqlDbConnection)), get(named(postgresqlDbConnection))) } bind DemoRepository::class
}

val testDbConnectionModule = module {
    single { MySqlDbConnection.load(get()) } withOptions {
        named(mySqlDbConnection)
        bind<DbConnection>()
    }
    single { PostgreSqlDbConnection.load(get()) } withOptions {
        named(postgresqlDbConnection)
        bind<DbConnection>()
    }
}

val testConfigModule = module {
    single { HoconApplicationConfig(ConfigFactory.load()) } bind ApplicationConfig::class
}