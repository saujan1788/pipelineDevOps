package com.saujan.microservices.productservice;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.distribution.Version;
import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductServiceApplicationTests {

    private static MongodExecutable mongodExecutable;
    private static String bindIp = "localhost";
    private static int port = 27017;

    @BeforeAll
    static void setup() throws Exception {
        MongodStarter starter = MongodStarter.getDefaultInstance();
        MongodConfig mongodConfig = MongodConfig.builder()
            .version(Version.Main.PRODUCTION)
            .build();

        mongodExecutable = starter.prepare(mongodConfig);
        mongodExecutable.start();
    }

    @AfterAll
    static void cleanup() {
        if (mongodExecutable != null) {
            mongodExecutable.stop();
        }
    }

    @DynamicPropertySource
    static void mongoProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.data.mongodb.uri", () -> "mongodb://" + bindIp + ":" + port);
    }

    @LocalServerPort
    private int localPort;

    @BeforeEach
    void initialize() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = localPort;
    }

    @Test
    void shouldCreateProduct() {
        String requestBody = """
            {
                "name": "iPhone_15",
                "description": "iPhone 15 is a smartphone from Apple",
                "price": 1000
            }
        """;

        RestAssured.given().contentType("application/json")
                .body(requestBody)
                .when()
                .post("/api/product")
                .then()
                .statusCode(201)
                .body("id", Matchers.notNullValue())
                .body("name", Matchers.equalTo("iPhone_15"))
                .body("description", Matchers.equalTo("iPhone 15 is a smartphone from Apple"))
                .body("price", Matchers.equalTo(1000));
    }
}
