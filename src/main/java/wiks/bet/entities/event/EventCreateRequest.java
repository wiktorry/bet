package wiks.bet.entities.event;

import wiks.bet.entities.EventType;

import java.util.Date;

public record EventCreateRequest(String homeTeam, String awayTeam, Date date, EventType type) {

}
