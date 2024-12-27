package wiks.bet.services;

import org.springframework.stereotype.Service;
import wiks.bet.entities.EventType;
import wiks.bet.entities.Team;
import wiks.bet.repositories.TeamRepository;

import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team findOrAddTeam(String name, EventType sport) {
        Optional<Team> teamOptional = teamRepository.findByNameAndSport(name, sport);
        if (teamOptional.isPresent()) {
            return teamOptional.get();
        }
        Team team = new Team(
                0,
                name,
                sport
        );
        return teamRepository.save(team);
    }
}
