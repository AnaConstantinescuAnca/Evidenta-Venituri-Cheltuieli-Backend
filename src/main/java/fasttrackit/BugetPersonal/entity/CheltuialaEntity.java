package fasttrackit.BugetPersonal.entity;

import fasttrackit.BugetPersonal.enums.TipCheltuiala;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/*
 * Object Cheltuieli
 */
@Entity
@Getter
@Setter
@Table(name = "cheltuieli")
@NoArgsConstructor

public class CheltuialaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private double valoare;

    @Temporal(value = TemporalType.DATE)
    @Column
    private Date data;

    @Column
    private TipCheltuiala tip;

    public CheltuialaEntity(int id, double valoare, Date data, TipCheltuiala tip) {
        this.id = id;
        this.valoare = valoare;
        this.data = data;
        this.tip = tip;
    }
}
