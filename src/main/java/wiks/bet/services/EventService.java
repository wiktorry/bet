package wiks.bet.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import wiks.bet.entities.Team;
import wiks.bet.entities.bet.Bet;
import wiks.bet.entities.bet.BetGetResponse;
import wiks.bet.entities.event.Event;
import wiks.bet.entities.event.EventCreateRequest;
import wiks.bet.entities.event.EventGetResponse;
import wiks.bet.repositories.EventRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class EventService {
    private final EventRepository eventRepository;
    private final TeamService teamService;
    private final ModelMapper modelMapper;

    public EventService(EventRepository eventRepository, TeamService teamService, ModelMapper modelMapper) {
        this.eventRepository = eventRepository;
        this.teamService = teamService;
        this.modelMapper = modelMapper;
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

    public List<EventGetResponse> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        List<EventGetResponse> response = new ArrayList<>();
        events.forEach(
                event -> {
                    List<Bet> bets = event.getBets();
                    if (event.getBets().size() >= 3) {
                        bets = event.getBets().subList(0, 3);
                    }
                    List<BetGetResponse> betsResponse = bets.stream()
                            .map(bet -> modelMapper.map(bet, BetGetResponse.class))
                            .toList();
                    EventGetResponse eventResponse = new EventGetResponse(
                            event.getHomeTeam().getName(),
                            event.getAwayTeam().getName(),
                            event.getDate(),
                            event.getType(),
                            betsResponse
                    );
                    response.add(eventResponse);
                }
        );
        return response;
    }
}
