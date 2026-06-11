package starfleet_api.repository;

import starfleet_api.model.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {
    long countByShipId(long shipId);
}