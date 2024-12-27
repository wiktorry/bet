package wiks.bet.rest;

import org.springframework.web.bind.annotation.*;
import wiks.bet.entities.event.Event;
import wiks.bet.entities.event.EventCreateRequest;
import wiks.bet.entities.event.EventGetResponse;
import wiks.bet.services.EventService;

import java.util.List;

@RestController
@RequestMapping("/betting/event")
public class EventController {
    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public Event createEvent(@RequestBody EventCreateRequest event) {
        return eventService.addEvent(event);
    }

    @GetMapping
    public List<EventGetResponse> getEvents() {
        return eventService.getAllEvents();
    }
}
