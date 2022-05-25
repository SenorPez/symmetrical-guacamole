package com.senorpez.avt.api;

import com.senorpez.avt.api.entities.ShipEntity;
import com.senorpez.avt.api.models.ShipModel;
import com.senorpez.avt.shipdesigner.Ship;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.session.Session;

import static com.senorpez.avt.shipdesigner.HullShape.SPHERE;

@Controller("/ship")
public class ShipController {
    private static final String ATTR_SHIP = "ship";

    @Get()
    HttpResponse<ShipModel> getShip(Session session) {
        Ship ship = session.get(ATTR_SHIP, Ship.class)
                .orElseGet(() -> {
                    Ship newShip = new Ship()
                            .setHullSpaces(25)
                            .setHullShape(SPHERE)
                            .setDriveGeneration(3.4)
                            .setThrust(6);
                    newShip.externalArmor = 0; // TODO: Change after implementing Systems object.
                    newShip.internalArmor = 4; // TODO: Change after implementing System object.
                    newShip.build();
                    session.put(ATTR_SHIP, newShip);
                    return newShip;
                });
        ShipEntity shipEntity = new ShipEntity(ship);
        ShipModel shipModel = new ShipModel(shipEntity);
        return HttpResponse.ok(shipModel);
    }
}