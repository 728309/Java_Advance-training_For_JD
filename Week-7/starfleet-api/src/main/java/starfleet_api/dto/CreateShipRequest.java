package starfleet_api.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateShipRequest {
    private String name;
    private String classType;
    private int crewCapacity;
}