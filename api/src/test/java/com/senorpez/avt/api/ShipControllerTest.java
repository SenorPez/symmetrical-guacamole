package com.senorpez.avt.api;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest
class ShipControllerTest {
    @Inject
    @Client("/")
    HttpClient client;

    @Test
    void getShip() {
        HttpRequest<String> request = HttpRequest.GET("/ships");
        String body = client.toBlocking().retrieve(request);

        assertNotNull(body);
        assertEquals("{\"ship:\": \"The Ship\"", body);
    }
}