package wiks.bet.rest;

import org.springframework.web.bind.annotation.*;
import wiks.bet.entities.bet.BetCreateRequest;
import wiks.bet.entities.bet.BetGetResponse;
import wiks.bet.services.BetService;

import java.util.List;

@RestController
@RequestMapping("/betting/events")
public class BetController {
    private final BetService betService;

    public BetController(BetService betService) {
        this.betService = betService;
    }

    @PostMapping("/{eventId}/bets")
    public List<BetGetResponse> addBets(@PathVariable int eventId, @RequestBody List<BetCreateRequest> bets) {
        return betService.addBets(eventId, bets);
    }

}
