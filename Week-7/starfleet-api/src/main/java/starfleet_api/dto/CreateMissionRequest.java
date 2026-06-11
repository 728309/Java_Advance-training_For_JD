package starfleet_api.dto;

import lombok.Getter;
import lombok.Setter;
import starfleet_api.model.MissionStatus;

import java.time.LocalDate;

@Getter
@Setter
public class CreateMissionRequest {
    private String name;
    private long shipId;
    private LocalDate startDate;
    private MissionStatus status;
}