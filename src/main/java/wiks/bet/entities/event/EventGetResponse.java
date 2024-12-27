package wiks.bet.entities.event;

import wiks.bet.entities.Bet;
import wiks.bet.entities.EventType;

import java.util.Date;
import java.util.List;

public record EventGetResponse(String homeTeam, String awayTeam, Date date, EventType type, List<Bet> bets) {
}
