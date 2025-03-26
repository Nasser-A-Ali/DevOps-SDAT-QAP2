package com.keyin.rest.tournament;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TournamentRepository extends CrudRepository<Tournament, Long> {
    public List<Tournament> findByLocationContainingIgnoreCase(String location);
    public List<Tournament> findByStartDate(Date startDate);
    public List<Tournament> findByStartDateBetween(Date startDate, Date endDate);
}