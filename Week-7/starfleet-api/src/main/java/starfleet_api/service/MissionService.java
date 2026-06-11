package starfleet_api.service;

import org.springframework.stereotype.Service;
import starfleet_api.dto.CreateMissionRequest;
import starfleet_api.dto.UpdateMissionRequest;
import starfleet_api.model.Mission;
import starfleet_api.model.Ship;
import starfleet_api.repository.MissionRepository;
import starfleet_api.repository.ShipRepository;
import starfleet_api.dto.MissionHistoryResponse;
import starfleet_api.model.MissionStatus;

import java.util.Comparator;
import java.util.List;

@Service
public class MissionService {
    private final MissionRepository missionRepository;
    private final ShipRepository shipRepository;

    public MissionService(
            MissionRepository missionRepository,
            ShipRepository shipRepository
    ) {
        this.missionRepository = missionRepository;
        this.shipRepository = shipRepository;
    }

    public Mission createMission(CreateMissionRequest request) {
        Ship ship = shipRepository.findById(request.getShipId())
                .orElseThrow(() -> new IllegalArgumentException("Ship not found"));

        Mission mission = new Mission();
        mission.setName(request.getName());
        mission.setStartDate(request.getStartDate());
        mission.setStatus(request.getStatus());
        mission.setShip(ship);

        return missionRepository.save(mission);
    }

    public Mission updateMission(long id, UpdateMissionRequest request) {
        Mission mission = missionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Mission not found"));

        mission.setName(request.getName());
        mission.setEndDate(request.getEndDate());
        mission.setStatus(request.getStatus());

        return missionRepository.save(mission);
    }

    public List<MissionHistoryResponse> getMissionHistory() {
        return missionRepository.findAll()
                .stream()
                .filter(mission -> mission.getStatus() == MissionStatus.COMPLETED
                        || mission.getStatus() == MissionStatus.CANCELED)
                .sorted(Comparator.comparing(Mission::getEndDate).reversed())
                .map(mission -> new MissionHistoryResponse(
                        mission.getId(),
                        mission.getName(),
                        mission.getShip().getName(),
                        mission.getStartDate(),
                        mission.getEndDate(),
                        mission.getStatus()
                ))
                .toList();
    }
}