package com.senorpez.avt.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.senorpez.avt.api.entities.ShipEntity;
import com.senorpez.avt.api.models.ShipModel;
import com.senorpez.avt.shipdesigner.Ship;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Put;
import io.micronaut.session.Session;

import java.util.function.Supplier;

import static com.senorpez.avt.shipdesigner.HullShape.SPHERE;

@Controller("/ship")
public class ShipController {
    private static final String ATTR_SHIP = "ship";

    private final Supplier<Ship> defaultShip = () -> {
        Ship newShip = new Ship()
                .setHullSpaces(25)
                .setHullShape(SPHERE)
                .setDriveGeneration(3.4)
                .setThrust(6.0);
        newShip.externalArmor = 0; // TODO: Change after implementing Systems object.
        newShip.internalArmor = 4; // TODO: Change after implementing System object.
        newShip.build();
        return newShip;
    };

    @Get()
    HttpResponse<ShipModel> getShip(Session session) {
        Ship ship = session.get(ATTR_SHIP, Ship.class)
                .orElseGet(() -> {
                    Ship newShip = defaultShip.get();
                    session.put(ATTR_SHIP, newShip);
                    return newShip;
                });
        ShipEntity shipEntity = new ShipEntity(ship);
        ShipModel shipModel = new ShipModel(shipEntity);
        return HttpResponse.ok(shipModel);
    }

    @Put(value = "/shipData", consumes = MediaType.APPLICATION_JSON)
    HttpResponse<ShipModel> putShipData(Session session, @Body String json) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(json);
        JsonNode hullSpaces = root.get("hullSpaces");
        JsonNode driveGeneration = root.get("driveGeneration");

        Ship ship = session.get(ATTR_SHIP, Ship.class)
                .orElseGet(() -> {
                    Ship newShip = defaultShip.get();
                    session.put(ATTR_SHIP, newShip);
                    return newShip;
                });
        ship.setHullSpaces(hullSpaces.asInt()).build();
        ship.setDriveGeneration(driveGeneration.asDouble()).build();
        ShipEntity shipEntity = new ShipEntity(ship);
        ShipModel shipModel = new ShipModel(shipEntity);
        return HttpResponse.ok(shipModel);
    }
}