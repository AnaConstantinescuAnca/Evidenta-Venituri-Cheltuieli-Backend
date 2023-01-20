package fasttrackit.BugetPersonal.entity;

import fasttrackit.BugetPersonal.enums.TipVenit;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/*
 * Object Venituri
 */
@Entity
@Getter
@Setter
@Table(name = "venituri")
@NoArgsConstructor
public class VenitEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private int valoare;
    @Temporal(value = TemporalType.DATE)
    @Column
    private Date data;
    @Column
    private TipVenit tip;

    public VenitEntity(int id, int valoare, Date data, TipVenit tip) {
        this.id = id;
        this.valoare = valoare;
        this.data = data;
        this.tip = tip;
    }

}
