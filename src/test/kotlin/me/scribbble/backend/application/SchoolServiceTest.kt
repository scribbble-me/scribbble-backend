package me.scribbble.backend.application

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.shouldHaveSize
import me.scribbble.backend.domain.school.School
import me.scribbble.backend.domain.school.SchoolRepository
import me.scribbble.backend.support.annotation.ServiceTest

@ServiceTest
class SchoolServiceTest(
    private val schoolService: SchoolService,
    private val schoolRepository: SchoolRepository
) : StringSpec({

    "학교를 검색한다" {
        // given
        schoolRepository.save(School("XXX 초등학교"))
        schoolRepository.save(School("OOO 중학교"))
        schoolRepository.save(School("XXX 고등학교"))
        schoolRepository.save(School("OOO 대학교"))

        // when
        val actual = schoolService.searchSchool("XXX")

        // then
        actual shouldHaveSize 2
    }
})
