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

    final double tolerance = 1e-5;

    @Test
    void getShip() {
        HttpRequest<String> request = HttpRequest.GET("/ship");
        String response = client.toBlocking().retrieve(request);

        double expectedValue;
        JsonPath jsonPath = new JsonPath(response);

        double hullDiameter = jsonPath.getDouble("hullDiameter");
        expectedValue = 14.94895d;
        assertEquals(expectedValue, hullDiameter, tolerance);

        double mastLength = jsonPath.getDouble("mastLength");
        expectedValue = 28.20816d;
        assertEquals(expectedValue, mastLength, tolerance);

        double mastDiameter = jsonPath.getDouble("mastDiameter");
        expectedValue = 1.88054d;
        assertEquals(expectedValue, mastDiameter, tolerance);

        double shieldMaxDiameter = jsonPath.getDouble("shieldMaxDiameter");
        expectedValue = 2.99936d;
        assertEquals(expectedValue, shieldMaxDiameter, tolerance);

        double shieldMinDiameter = jsonPath.getDouble("shieldMinDiameter");
        expectedValue = 2.02292d;
        assertEquals(expectedValue, shieldMinDiameter, tolerance);

        double shieldThickness = jsonPath.getDouble("shieldThickness");
        expectedValue = 2.64379d;
        assertEquals(expectedValue, shieldThickness, tolerance);

        double lanternDiameter = jsonPath.getDouble("lanternDiameter");
        expectedValue = 10.95445d;
        assertEquals(expectedValue, lanternDiameter, tolerance);

        Integer t = 1;
    }
}