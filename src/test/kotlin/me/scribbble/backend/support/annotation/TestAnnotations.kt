package me.scribbble.backend.support.annotation

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.transaction.annotation.Transactional

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@Transactional
@SpringBootTest
annotation class ServiceTest
