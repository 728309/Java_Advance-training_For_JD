package starfleet_api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import starfleet_api.model.MissionStatus;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MissionHistoryResponse {
    private long id;
    private String name;
    private String shipName;
    private LocalDate startDate;
    private LocalDate endDate;
    private MissionStatus status;
}