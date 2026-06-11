package starfleet_api.service;

import org.springframework.stereotype.Service;
import starfleet_api.dto.CreateCrewMemberRequest;
import starfleet_api.model.CrewMember;
import starfleet_api.model.Ship;
import starfleet_api.repository.CrewMemberRepository;
import starfleet_api.repository.ShipRepository;

@Service
public class CrewMemberService {
    private final CrewMemberRepository crewMemberRepository;
    private final ShipRepository shipRepository;

    public CrewMemberService(
            CrewMemberRepository crewMemberRepository,
            ShipRepository shipRepository
    ) {
        this.crewMemberRepository = crewMemberRepository;
        this.shipRepository = shipRepository;
    }

    public CrewMember createCrewMember(CreateCrewMemberRequest request) {
        Ship ship = shipRepository.findById(request.getShipId())
                .orElseThrow(() -> new IllegalArgumentException("Ship not found"));

        long currentCrewSize = crewMemberRepository.countByShipId(ship.getId());

        if (currentCrewSize >= ship.getCrewCapacity()) {
            throw new IllegalStateException("Ship has no available crew capacity");
        }

        CrewMember crewMember = new CrewMember();
        crewMember.setName(request.getName());
        crewMember.setRank(request.getRank());
        crewMember.setShip(ship);

        return crewMemberRepository.save(crewMember);
    }
}