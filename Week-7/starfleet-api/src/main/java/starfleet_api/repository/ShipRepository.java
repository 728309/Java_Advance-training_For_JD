package starfleet_api.repository;

import starfleet_api.model.Ship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShipRepository extends JpaRepository<Ship, Long> {
}