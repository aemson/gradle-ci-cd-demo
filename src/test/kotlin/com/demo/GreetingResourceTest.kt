package com.demo

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class GreetingResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
            .`when`().get("/hello?name=Aemson")
            .then()
            .statusCode(200)
            .body(`is`("Hello from Reactive Aemson"))
    }

    @Test
    fun shouldReturnAllUsers() {
        given()
            .`when`().get("/hello/all")
            .then()
            .statusCode(200)
    }
}
