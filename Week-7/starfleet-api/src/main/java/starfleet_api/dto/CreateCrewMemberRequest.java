package starfleet_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCrewMemberRequest {
    private String name;
    private String rank;
    private long shipId;
}