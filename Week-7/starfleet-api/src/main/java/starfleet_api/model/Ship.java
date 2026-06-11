package starfleet_api.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Ship {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String classType;
    private int crewCapacity;

    @OneToMany(mappedBy = "ship", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "ship-crew")
    private List<CrewMember> crewMembers = new ArrayList<>();

    @OneToMany(mappedBy = "ship", cascade = CascadeType.ALL)
    @JsonManagedReference(value = "ship-missions")
    private List<Mission> missions = new ArrayList<>();
}