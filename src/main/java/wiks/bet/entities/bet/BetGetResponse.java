package wiks.bet.entities.bet;

public record BetGetResponse(String name, float odds, boolean active, int eventId) {
}
