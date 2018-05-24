package model;

import javax.persistence.*;
import javax.validation.constraints.AssertTrue;

@Entity
@Table(
        uniqueConstraints = @UniqueConstraint(columnNames = {"start_id", "ende_id"})
)
public class Strecke {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ID;
	@ManyToOne
	private Bahnhof start;
	@ManyToOne
	private Bahnhof ende;


    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public Bahnhof getStart() {
        return start;
    }

    public void setStart(Bahnhof start) {
        this.start = start;
    }

    public Bahnhof getEnde() {
        return ende;
    }

    public void setEnde(Bahnhof ende) {
        this.ende = ende;
    }
}
