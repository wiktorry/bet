package wiks.bet.services;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import wiks.bet.entities.bet.Bet;
import wiks.bet.entities.bet.BetCreateRequest;
import wiks.bet.entities.bet.BetGetResponse;
import wiks.bet.entities.event.Event;
import wiks.bet.repositories.BetRepository;
import wiks.bet.repositories.EventRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class BetService {
    private final BetRepository betRepository;
    private final EventRepository eventRepository;
    private final ModelMapper modelMapper;

    public BetService(BetRepository betRepository, EventRepository eventRepository, ModelMapper modelMapper) {
        this.betRepository = betRepository;
        this.eventRepository = eventRepository;
        this.modelMapper = modelMapper;
    }

    public List<BetGetResponse> addBets(int eventId, List<BetCreateRequest> bets) {
        List<Bet> newBets = new ArrayList<>();
        Event event = eventRepository.findById(eventId).orElse(null);
        bets.forEach(betRequest -> {
            Bet newBet = new Bet(
                    0,
                    betRequest.name(),
                    betRequest.odds(),
                    true,
                    event
            );
            event.addBet(newBet);
            newBets.add(newBet);
        });
        List<Bet> createdBets = betRepository.saveAll(newBets);
        List<BetGetResponse> response = createdBets.stream()
                .map(bet -> modelMapper.map(bet, BetGetResponse.class))
                .toList();
        return response;
    }
}
