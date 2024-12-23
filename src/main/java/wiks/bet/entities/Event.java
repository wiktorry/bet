package wiks.bet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Map;

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
    @ElementCollection
    @CollectionTable(name = "betsMapping",
            joinColumns = {@JoinColumn(name = "eventId", referencedColumnName = "id")})
    @MapKeyColumn(name = "betName")
    @Column(name = "odds")
    private Map<String, Double> bets;
}
