package me.scribbble.backend.application

import me.scribbble.backend.domain.school.SchoolRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class SchoolService(
    private val schoolRepository: SchoolRepository
) {

    fun searchSchool(query: String): List<SchoolResponse> {
        val schools = schoolRepository.findTop5ByNameContaining(query)
        return schools.map { school -> SchoolResponse(school.id, school.name) }
    }
}
