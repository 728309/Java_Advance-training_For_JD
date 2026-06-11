package starfleet_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import starfleet_api.dto.CreateShipRequest;
import starfleet_api.model.Ship;
import starfleet_api.service.ShipService;

import java.util.List;

@RestController
@RequestMapping("/ships")
public class ShipController {
    private final ShipService shipService;

    public ShipController(ShipService shipService) {
        this.shipService = shipService;
    }

    @GetMapping
    public ResponseEntity<List<Ship>> getAllShips() {
        return ResponseEntity.ok(shipService.getAllShips());
    }

    @PostMapping
    public ResponseEntity<Ship> createShip(@RequestBody CreateShipRequest request) {
        Ship createdShip = shipService.createShip(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdShip);
    }
}