package wiks.bet;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import wiks.bet.entities.EventType;
import wiks.bet.entities.Team;
import wiks.bet.entities.event.Event;
import wiks.bet.entities.event.EventCreateRequest;
import wiks.bet.repositories.EventRepository;
import wiks.bet.repositories.TeamRepository;

import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class EventIntegrationTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private TeamRepository teamRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    @Container
    @ServiceConnection
    private static final MySQLContainer<?> container = new MySQLContainer<>("mysql");

    @BeforeEach
    void setUp() {
        Team homeTeam = new Team(
                0,
                "FC Barcelona",
                EventType.FOOTBALL
        );
        Team awayTeam = new Team(
                0,
                "Real Madrid",
                EventType.FOOTBALL
        );
        teamRepository.saveAll(List.of(homeTeam, awayTeam));
        Event event = new Event(
                0,
                homeTeam,
                awayTeam,
                new Date(),
                EventType.FOOTBALL,
                null
        );
        eventRepository.save(event);
    }

    @AfterEach
    void tearDown() {
        eventRepository.deleteAll();
    }

    @Test
    void shouldCreateEventWithZeroNewTeams() throws Exception {
        EventCreateRequest event = new EventCreateRequest(
                "FC Barcelona",
                "Real Madrid",
                new Date(),
                EventType.FOOTBALL
        );
        MvcResult result = mockMvc.perform(post("/betting/event")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(event)))
                .andExpect(status().isOk())
                .andReturn();
        String response = result.getResponse().getContentAsString();
        Event eventFromJson = objectMapper.readValue(response, Event.class);
        Team homeTeam = eventFromJson.getHomeTeam();
        Assertions.assertEquals(homeTeam.getName(), "FC Barcelona");
        Assertions.assertEquals(teamRepository.count(), 2);
        Assertions.assertEquals(eventRepository.count(), 2);
    }
}