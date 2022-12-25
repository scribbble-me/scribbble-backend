package me.scribbble.backend.ui

import me.scribbble.backend.application.SchoolResponse
import me.scribbble.backend.application.SchoolService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RequestMapping("/api/schools")
@RestController
class SchoolController(
    private val schoolService: SchoolService
) {

    @GetMapping
    fun searchSchools(@RequestParam query: String): ResponseEntity<List<SchoolResponse>> {
        val response = schoolService.searchSchool(query)
        return ResponseEntity.ok(response)
    }
}
