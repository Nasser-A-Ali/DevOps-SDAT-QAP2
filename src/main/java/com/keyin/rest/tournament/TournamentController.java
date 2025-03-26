package com.keyin.rest.tournament;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class TournamentController {
    @Autowired
    private TournamentService tournamentService;

    @GetMapping("/tournaments")
    public List<Tournament> getAllTournaments() {
        return tournamentService.getAllTournaments();
    }

    @PostMapping("/tournament")
    public Tournament createTournament(@RequestBody Tournament tournament) {
        return tournamentService.createTournament(tournament);
    }

    @GetMapping("/tournament/{id}")
    public Tournament getTournamentById(@PathVariable Long id) {
        return tournamentService.getTournamentById(id).orElse(null);
    }

    @PutMapping("/tournament/{id}")
    public Tournament updateTournament(@PathVariable Long id, @RequestBody Tournament tournamentDetails) {
        return tournamentService.updateTournament(id, tournamentDetails);
    }

    @DeleteMapping("/tournament/{id}")
    public void deleteTournament(@PathVariable Long id) {
        tournamentService.deleteTournament(id);
    }

    @GetMapping("/tournament_search")
    public List<Tournament> searchTournaments(
            @RequestParam(value = "location", required = false) String location,
            @RequestParam(value = "start_date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(value = "end_date", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {

        if (location != null) {
            return tournamentService.searchByLocation(location);
        } else if (startDate != null && endDate != null) {
            return tournamentService.searchBetweenDates(startDate, endDate);
        } else if (startDate != null) {
            return tournamentService.searchByStartDate(startDate);
        }

        return tournamentService.getAllTournaments();
    }
}