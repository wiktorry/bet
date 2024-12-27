package wiks.bet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wiks.bet.entities.EventType;
import wiks.bet.entities.Team;

import java.util.Optional;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
    Optional<Team> findByNameAndSport(String name, EventType sport);
}
