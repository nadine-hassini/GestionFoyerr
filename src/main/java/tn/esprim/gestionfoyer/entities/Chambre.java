package tn.esprim.gestionfoyer.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Chambre implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idChambre;
    private Long numeroChampre;
    @Enumerated(EnumType.STRING)
    private TypeChambre TypeC;
    @ManyToOne
    private Bloc bloc;
    @OneToMany(cascade = CascadeType.ALL , fetch =FetchType.EAGER)
    private List<Reservation> reservations;

    public Long getIdChambre() {
        return idChambre;
    }

    public void setIdChambre(Long idChambre) {
        this.idChambre = idChambre;
    }

    public Long getNumeroChampre() {
        return numeroChampre;
    }

    public void setNumeroChampre(Long numeroChampre) {
        this.numeroChampre = numeroChampre;
    }

    public TypeChambre getTypeC() {
        return TypeC;
    }

    public void setTypeC(TypeChambre typeC) {
        TypeC = typeC;
    }
}
