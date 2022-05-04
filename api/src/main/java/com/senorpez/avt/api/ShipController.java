package com.senorpez.avt.api;

import com.senorpez.avt.api.entities.ShipEntity;
import com.senorpez.avt.api.models.ShipModel;
import com.senorpez.avt.shipdesigner.Ship;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.session.Session;

@Controller("/ship")
public class ShipController {
    private static final String ATTR_SHIP = "ship";

    @Get("/hullLength")
    HttpResponse<ShipModel> getHullLength(Session session) {
        Ship ship = session.get(ATTR_SHIP, Ship.class)
                .orElseGet(() -> {
                    Ship newShip = new Ship().build();
                    session.put(ATTR_SHIP, newShip);
                    return newShip;
                });
        ShipEntity shipEntity = new ShipEntity(ship);
        ShipModel shipModel = new ShipModel(shipEntity);
        return HttpResponse.ok(shipModel);
    }
}