package me.scribbble.backend.acceptance.support

import io.restassured.RestAssured
import io.restassured.response.ExtractableResponse
import io.restassured.response.Response
import me.scribbble.backend.support.DatabaseCleaner
import org.junit.jupiter.api.BeforeEach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.MediaType

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class AcceptanceTest {

    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private lateinit var databaseCleaner: DatabaseCleaner

    @BeforeEach
    fun setUpRestAssured() {
        RestAssured.port = port;
        databaseCleaner.clear()
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

