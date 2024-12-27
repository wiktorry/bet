package wiks.bet.entities.event;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wiks.bet.entities.Bet;
import wiks.bet.entities.EventType;
import wiks.bet.entities.Team;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "events")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @ManyToOne
    @JoinColumn(name = "homeTeamId")
    private Team homeTeam;
    @ManyToOne
    @JoinColumn(name = "awayTeamId")
    private Team awayTeam;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;
    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private EventType type;
    @OneToMany(mappedBy = "event")
    private List<Bet> bets;

    public void addBet(Bet bet) {
        if (this.bets == null) {
            this.bets = new ArrayList<Bet>();
        }
        this.bets.add(bet);
    }
}
