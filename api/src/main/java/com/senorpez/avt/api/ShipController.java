package com.senorpez.avt.api;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;

@Controller("/ships")
public class ShipController {
    @Get
    public HttpResponse<String> getShip() {
        return HttpResponse
                .ok()
                .body("{\"ship:\": \"The Ship\"");
    }
}
