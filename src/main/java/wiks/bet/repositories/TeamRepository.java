package wiks.bet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wiks.bet.entities.Team;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {
}
