package wiks.bet.entities;

import java.util.Date;

public record EventCreateRequest(String homeTeam, String awayTeam, Date date, EventType type) {

}
