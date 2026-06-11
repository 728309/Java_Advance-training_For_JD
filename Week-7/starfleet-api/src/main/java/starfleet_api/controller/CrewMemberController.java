package starfleet_api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import starfleet_api.dto.CreateCrewMemberRequest;
import starfleet_api.model.CrewMember;
import starfleet_api.service.CrewMemberService;

@RestController
@RequestMapping("/crewmembers")
public class CrewMemberController {
    private final CrewMemberService crewMemberService;

    public CrewMemberController(CrewMemberService crewMemberService) {
        this.crewMemberService = crewMemberService;
    }

    @PostMapping
    public ResponseEntity<?> createCrewMember(@RequestBody CreateCrewMemberRequest request) {
        try {
            CrewMember createdCrewMember = crewMemberService.createCrewMember(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdCrewMember);
        } catch (IllegalStateException exception) {
            return ResponseEntity.badRequest().body(exception.getMessage());
        } catch (IllegalArgumentException exception) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
    }
}