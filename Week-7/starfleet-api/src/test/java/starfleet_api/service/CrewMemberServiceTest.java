package starfleet_api.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import starfleet_api.dto.CreateCrewMemberRequest;
import starfleet_api.model.CrewMember;
import starfleet_api.model.Ship;
import starfleet_api.repository.CrewMemberRepository;
import starfleet_api.repository.ShipRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CrewMemberServiceTest {
    private CrewMemberRepository crewMemberRepository;
    private ShipRepository shipRepository;
    private CrewMemberService crewMemberService;

    @BeforeEach
    void setUp() {
        crewMemberRepository = mock(CrewMemberRepository.class);
        shipRepository = mock(ShipRepository.class);
        crewMemberService = new CrewMemberService(crewMemberRepository, shipRepository);
    }

    @Test
    void createCrewMember_shouldSaveCrewMember_whenShipHasCapacity() {
        Ship ship = new Ship();
        ship.setId(1L);
        ship.setName("Enterprise");
        ship.setClassType("Constitution");
        ship.setCrewCapacity(2);

        CreateCrewMemberRequest request = new CreateCrewMemberRequest();
        request.setName("James T Kirk");
        request.setRank("Captain");
        request.setShipId(1L);

        when(shipRepository.findById(1L)).thenReturn(Optional.of(ship));
        when(crewMemberRepository.countByShipId(1L)).thenReturn(1L);
        when(crewMemberRepository.save(any(CrewMember.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CrewMember result = crewMemberService.createCrewMember(request);

        assertEquals("James T Kirk", result.getName());
        assertEquals("Captain", result.getRank());
        assertEquals(ship, result.getShip());

        verify(crewMemberRepository).save(any(CrewMember.class));
    }

    @Test
    void createCrewMember_shouldThrowException_whenShipIsFull() {
        Ship ship = new Ship();
        ship.setId(1L);
        ship.setName("Enterprise");
        ship.setClassType("Constitution");
        ship.setCrewCapacity(2);

        CreateCrewMemberRequest request = new CreateCrewMemberRequest();
        request.setName("Leonard McCoy");
        request.setRank("Doctor");
        request.setShipId(1L);

        when(shipRepository.findById(1L)).thenReturn(Optional.of(ship));
        when(crewMemberRepository.countByShipId(1L)).thenReturn(2L);

        IllegalStateException exception = assertThrows(
                IllegalStateException.class,
                () -> crewMemberService.createCrewMember(request)
        );

        assertEquals("Ship has no available crew capacity", exception.getMessage());

        verify(crewMemberRepository, never()).save(any(CrewMember.class));
    }

    @Test
    void createCrewMember_shouldThrowException_whenShipDoesNotExist() {
        CreateCrewMemberRequest request = new CreateCrewMemberRequest();
        request.setName("Unknown");
        request.setRank("Cadet");
        request.setShipId(99L);

        when(shipRepository.findById(99L)).thenReturn(Optional.empty());

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> crewMemberService.createCrewMember(request)
        );

        assertEquals("Ship not found", exception.getMessage());

        verify(crewMemberRepository, never()).save(any(CrewMember.class));
    }
}