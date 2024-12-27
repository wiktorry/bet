package wiks.bet.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import wiks.bet.entities.Event;
import wiks.bet.entities.EventCreateRequest;
import wiks.bet.services.EventService;

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
}
