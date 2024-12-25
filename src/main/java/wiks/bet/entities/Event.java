package wiks.bet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @OneToOne
    @JoinColumn(name = "homeTeamId")
    private Team homeTeam;
    @OneToOne
    @JoinColumn(name = "awayTeamId")
    private Team awayTeam;
    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;
    @OneToMany(mappedBy = "event")
    private List<Bet> bets;

    public void addBet(Bet bet) {
        if (this.bets == null) {
            this.bets = new ArrayList<Bet>();
        }
        this.bets.add(bet);
    }
}
