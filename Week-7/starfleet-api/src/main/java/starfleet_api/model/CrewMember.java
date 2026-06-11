package starfleet_api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CrewMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private String rank;

    @ManyToOne
    @JoinColumn(name = "ship_id")
    @JsonBackReference(value = "ship-crew")
    private Ship ship;
}