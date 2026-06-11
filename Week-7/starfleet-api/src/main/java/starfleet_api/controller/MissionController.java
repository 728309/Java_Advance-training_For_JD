package starfleet_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import starfleet_api.dto.CreateMissionRequest;
import starfleet_api.dto.UpdateMissionRequest;
import starfleet_api.model.Mission;
import starfleet_api.service.MissionService;

@RestController
@RequestMapping("/missions")
public class MissionController {
    private final MissionService missionService;

    public MissionController(MissionService missionService) {
        this.missionService = missionService;
    }

    @PostMapping
    public ResponseEntity<?> createMission(@RequestBody CreateMissionRequest request) {
        try {
            Mission createdMission = missionService.createMission(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdMission);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateMission(
            @PathVariable long id,
            @RequestBody UpdateMissionRequest request
    ) {
        try {
            Mission updatedMission = missionService.updateMission(id, request);
            return ResponseEntity.ok(updatedMission);
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }

    @GetMapping("/history")
    public ResponseEntity<?> getMissionHistory() {
        return ResponseEntity.ok(missionService.getMissionHistory());
    }
}