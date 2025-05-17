package ma.zyn.app.bean.core.mensuel;

import java.util.List;





import ma.zyn.app.bean.core.commun.Secteur;
import ma.zyn.app.bean.core.commun.TypeEvenement;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "rapport_evenement_mensuel_secteur")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="rapport_evenement_mensuel_secteur_seq",sequenceName="rapport_evenement_mensuel_secteur_seq",allocationSize=1, initialValue = 1)
public class RapportEvenementMensuelSecteur  extends BaseEntity     {




    private BigDecimal total = BigDecimal.ZERO;

    private BigDecimal indicateurGlobal = BigDecimal.ZERO;

    private BigDecimal totalCumul = BigDecimal.ZERO;

    private BigDecimal indicateurGlobalCumul = BigDecimal.ZERO;

    private RapportEvenementMensuel rapportEvenementMensuel ;
    private Secteur secteur ;

    private List<RapportEvenementMensuelSecteurDetail> rapportEvenementMensuelSecteurDetails ;

    public RapportEvenementMensuelSecteur(){
        super();
    }

    public RapportEvenementMensuelSecteur(BigDecimal total, Secteur secteur) {
        this.total = total;
        this.secteur = secteur;
    }

    public RapportEvenementMensuelSecteur(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="rapport_evenement_mensuel_secteur_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rapport_evenement_mensuel")
    public RapportEvenementMensuel getRapportEvenementMensuel(){
        return this.rapportEvenementMensuel;
    }
    public void setRapportEvenementMensuel(RapportEvenementMensuel rapportEvenementMensuel){
        this.rapportEvenementMensuel = rapportEvenementMensuel;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "secteur")
    public Secteur getSecteur(){
        return this.secteur;
    }
    public void setSecteur(Secteur secteur){
        this.secteur = secteur;
    }
    public BigDecimal getTotal(){
        return this.total;
    }
    public void setTotal(BigDecimal total){
        this.total = total;
    }
    public BigDecimal getIndicateurGlobal(){
        return this.indicateurGlobal;
    }
    public void setIndicateurGlobal(BigDecimal indicateurGlobal){
        this.indicateurGlobal = indicateurGlobal;
    }
    public BigDecimal getTotalCumul(){
        return this.totalCumul;
    }
    public void setTotalCumul(BigDecimal totalCumul){
        this.totalCumul = totalCumul;
    }
    public BigDecimal getIndicateurGlobalCumul(){
        return this.indicateurGlobalCumul;
    }
    public void setIndicateurGlobalCumul(BigDecimal indicateurGlobalCumul){
        this.indicateurGlobalCumul = indicateurGlobalCumul;
    }
    @OneToMany(mappedBy = "rapportEvenementMensuelSecteur")
    public List<RapportEvenementMensuelSecteurDetail> getRapportEvenementMensuelSecteurDetails(){
        return this.rapportEvenementMensuelSecteurDetails;
    }

    public void setRapportEvenementMensuelSecteurDetails(List<RapportEvenementMensuelSecteurDetail> rapportEvenementMensuelSecteurDetails){
        this.rapportEvenementMensuelSecteurDetails = rapportEvenementMensuelSecteurDetails;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RapportEvenementMensuelSecteur rapportEvenementMensuelSecteur = (RapportEvenementMensuelSecteur) o;
        return id != null && id.equals(rapportEvenementMensuelSecteur.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

