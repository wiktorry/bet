package wiks.bet.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import wiks.bet.entities.Event;

@Repository
public interface EventRepository extends JpaRepository<Event, Integer> {
}
