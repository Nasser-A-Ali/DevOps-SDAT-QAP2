package com.keyin.rest.tournament;

import com.keyin.rest.member.Member;
import com.keyin.rest.member.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TournamentService {
    @Autowired
    private TournamentRepository tournamentRepository;

    @Autowired
    private MemberRepository memberRepository;  // Add this line

    public List<Tournament> getAllTournaments() {
        return (List<Tournament>) tournamentRepository.findAll();
    }

    public Tournament createTournament(Tournament tournament) {
        return tournamentRepository.save(tournament);
    }

    public Optional<Tournament> getTournamentById(Long id) {
        return tournamentRepository.findById(id);
    }

    public Tournament updateTournament(Long id, Tournament tournamentDetails) {
        Tournament tournament = tournamentRepository.findById(id).orElseThrow();
        tournament.setStartDate(tournamentDetails.getStartDate());
        tournament.setEndDate(tournamentDetails.getEndDate());
        tournament.setLocation(tournamentDetails.getLocation());
        tournament.setEntryFee(tournamentDetails.getEntryFee());
        tournament.setCashPrize(tournamentDetails.getCashPrize());

        if (tournamentDetails.getParticipatingMembers() != null) {
            // Clear existing members
            tournament.getParticipatingMembers().clear();

            // Add all new members
            for (Member member : tournamentDetails.getParticipatingMembers()) {
                // Verify member exists using the injected repository
                Member existingMember = memberRepository.findById(member.getId())
                        .orElseThrow(() -> new RuntimeException("Member not found with id: " + member.getId()));

                tournament.getParticipatingMembers().add(existingMember);
            }
        }

        return tournamentRepository.save(tournament);
    }

    public void deleteTournament(Long id) {
        tournamentRepository.deleteById(id);
    }

    public List<Tournament> searchByLocation(String location) {
        return tournamentRepository.findByLocationContainingIgnoreCase(location);
    }

    public List<Tournament> searchByStartDate(Date startDate) {
        return tournamentRepository.findByStartDate(startDate);
    }

    public List<Tournament> searchBetweenDates(Date startDate, Date endDate) {
        return tournamentRepository.findByStartDateBetween(startDate, endDate);
    }
}