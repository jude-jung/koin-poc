package com.example.application

import com.example.domain.repository.DemoRepository
import com.example.domain.service.DemoService

class DemoAppServiceImpl(
    private val demoService: DemoService,
    private val demoRepository: DemoRepository
): DemoAppService {
    override fun getDemoResult() {
        println("app service")
        demoService.getDemo()
        demoRepository.getDemo()
    }
}