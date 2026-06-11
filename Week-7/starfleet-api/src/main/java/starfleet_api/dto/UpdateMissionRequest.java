package starfleet_api.dto;

import lombok.Getter;
import lombok.Setter;
import starfleet_api.model.MissionStatus;

import java.time.LocalDate;

@Getter
@Setter
public class UpdateMissionRequest {
    private String name;
    private LocalDate endDate;
    private MissionStatus status;
}