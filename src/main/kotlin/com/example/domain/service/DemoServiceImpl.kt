package com.example.domain.service

import com.example.domain.repository.DemoRepository

class DemoServiceImpl(
    private val demoRepository: DemoRepository
) : DemoService {
    override fun getDemo() {
        println("service")
        demoRepository.getDemo()
    }
}