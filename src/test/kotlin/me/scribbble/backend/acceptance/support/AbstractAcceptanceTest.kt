package me.scribbble.backend.acceptance.support

import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import me.scribbble.backend.domain.school.School
import me.scribbble.backend.domain.school.SchoolRepository
import me.scribbble.backend.support.DatabaseCleaner
import me.scribbble.backend.support.annotation.AcceptanceTest
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType

@AcceptanceTest
abstract class AbstractAcceptanceTest {

    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private lateinit var databaseCleaner: DatabaseCleaner

    @Autowired
    private lateinit var schoolRepository: SchoolRepository

    @BeforeEach
    fun setUpRestAssured() {
        RestAssured.port = port;
        databaseCleaner.clear()
    }

    @BeforeEach
    fun setUpSchools() {
        schoolRepository.save(School("AA 초등학교"))
        schoolRepository.save(School("BB 초등학교"))
        schoolRepository.save(School("CC 중학교"))
        schoolRepository.save(School("DD 고등학교"))
        schoolRepository.save(School("EE 고등학교"))
        schoolRepository.save(School("FF 대학교"))
        // TODO: 더 좋은 방법을 생각해야함
    }

    fun get(url: String): ExtractableResponse<Response> {
        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .`when`().get(url)
            .then().log().all()
            .extract();
    }

    fun post(url: String, body: Any = mapOf<Any, Any>()): ExtractableResponse<Response> {
        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(body)
            .`when`().post(url)
            .then().log().all()
            .extract();
    }

    fun put(url: String, body: Any = mapOf<Any, Any>()): ExtractableResponse<Response> {
        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .body(body)
            .`when`().put(url)
            .then().log().all()
            .extract();
    }

    fun delete(url: String): ExtractableResponse<Response> {
        return RestAssured.given().log().all()
            .contentType(MediaType.APPLICATION_JSON_VALUE)
            .`when`().delete(url)
            .then().log().all()
            .extract();
    }
}

