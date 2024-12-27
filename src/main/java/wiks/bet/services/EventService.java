package wiks.bet.services;

import org.springframework.stereotype.Service;
import wiks.bet.entities.Event;
import wiks.bet.entities.EventCreateRequest;
import wiks.bet.entities.Team;
import wiks.bet.repositories.EventRepository;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final TeamService teamService;

    public EventService(EventRepository eventRepository, TeamService teamService) {
        this.eventRepository = eventRepository;
        this.teamService = teamService;
    }

    public Event addEvent(EventCreateRequest eventRequest) {
        Team homeTeam = teamService.findOrAddTeam(eventRequest.homeTeam(), eventRequest.type());
        Team awayTeam = teamService.findOrAddTeam(eventRequest.awayTeam(), eventRequest.type());
        Event event = new Event(
                0,
                homeTeam,
                awayTeam,
                eventRequest.date(),
                eventRequest.type(),
                null
        );
        return eventRepository.save(event);
    }
}
