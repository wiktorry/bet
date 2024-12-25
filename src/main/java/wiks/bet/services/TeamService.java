package wiks.bet.services;

import org.springframework.stereotype.Service;
import wiks.bet.entities.Team;
import wiks.bet.repositories.TeamRepository;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public Team addTeam(Team team) {
        return teamRepository.save(team);
    }
}
