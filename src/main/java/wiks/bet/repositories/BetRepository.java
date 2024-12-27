package wiks.bet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wiks.bet.entities.bet.Bet;

@Repository
public interface BetRepository extends JpaRepository<Bet, Integer> {
}
