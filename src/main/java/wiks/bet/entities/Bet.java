package wiks.bet.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import wiks.bet.entities.event.Event;

@Entity
@Table(name = "bets")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Bet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "odds")
    private float odds;
    @Column(name = "active")
    private boolean active;
    @ManyToOne
    @JoinColumn(name = "eventId")
    private Event event;
}
