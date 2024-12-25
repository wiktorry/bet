package wiks.bet.services;

import org.springframework.stereotype.Service;
import wiks.bet.entities.Event;
import wiks.bet.repositories.EventRepository;

import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final TeamService teamService;

    public EventService(EventRepository eventRepository, TeamService teamService) {
        this.eventRepository = eventRepository;
        this.teamService = teamService;
    }

    public Event addEvent(Event event) {
        List.of(event.getHomeTeam(), event.getAwayTeam()).forEach(teamService::addTeam);
        return eventRepository.save(event);
    }
}
