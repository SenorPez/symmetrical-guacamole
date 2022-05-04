package com.senorpez.avt.api;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.runtime.server.EmbeddedServer;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import io.restassured.path.json.JsonPath;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
class ShipControllerTest {
    @Inject
    EmbeddedServer server;

    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void getHullLength() {
        HttpRequest<String> request = HttpRequest.GET("/ship/hullLength");
        String response = client.toBlocking().retrieve(request);

        JsonPath jsonPath = new JsonPath(response);
        double hullLength = jsonPath.getDouble("hullLength");
        double expectedValue = 16.01118d;
        final double tolerance = 1e-5;

        assertEquals(expectedValue, hullLength, tolerance);
    }
}