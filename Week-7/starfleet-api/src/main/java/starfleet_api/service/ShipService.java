package starfleet_api.service;

import org.springframework.stereotype.Service;
import starfleet_api.dto.CreateShipRequest;
import starfleet_api.model.Ship;
import starfleet_api.repository.ShipRepository;

import java.util.List;

@Service
public class ShipService {
    private final ShipRepository shipRepository;

    public ShipService(ShipRepository shipRepository) {
        this.shipRepository = shipRepository;
    }

    public List<Ship> getAllShips() {
        return shipRepository.findAll();
    }

    public Ship createShip(CreateShipRequest request) {
        Ship ship = new Ship();
        ship.setName(request.getName());
        ship.setClassType(request.getClassType());
        ship.setCrewCapacity(request.getCrewCapacity());

        return shipRepository.save(ship);
    }
}