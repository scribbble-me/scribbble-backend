package me.scribbble.backend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ScribbbleBackendApplication

fun main(args: Array<String>) {
    runApplication<ScribbbleBackendApplication>(*args)
}
