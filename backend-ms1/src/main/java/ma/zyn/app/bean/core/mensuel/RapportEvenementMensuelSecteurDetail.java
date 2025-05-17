package ma.zyn.app.bean.core.mensuel;






import ma.zyn.app.bean.core.commun.TypeEvenement;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "rapport_evenement_mensuel_secteur_detail")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="rapport_evenement_mensuel_secteur_detail_seq",sequenceName="rapport_evenement_mensuel_secteur_detail_seq",allocationSize=1, initialValue = 1)
public class RapportEvenementMensuelSecteurDetail  extends BaseEntity     {




    private BigDecimal total = BigDecimal.ZERO;

    private BigDecimal indicateurGlobal = BigDecimal.ZERO;

    private RapportEvenementMensuelSecteur rapportEvenementMensuelSecteur ;
    private TypeEvenement typeEvenement ;


    public RapportEvenementMensuelSecteurDetail(){
        super();
    }

    public RapportEvenementMensuelSecteurDetail(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="rapport_evenement_mensuel_secteur_detail_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rapport_evenement_mensuel_secteur")
    public RapportEvenementMensuelSecteur getRapportEvenementMensuelSecteur(){
        return this.rapportEvenementMensuelSecteur;
    }
    public void setRapportEvenementMensuelSecteur(RapportEvenementMensuelSecteur rapportEvenementMensuelSecteur){
        this.rapportEvenementMensuelSecteur = rapportEvenementMensuelSecteur;
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
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_evenement")
    public TypeEvenement getTypeEvenement(){
        return this.typeEvenement;
    }
    public void setTypeEvenement(TypeEvenement typeEvenement){
        this.typeEvenement = typeEvenement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RapportEvenementMensuelSecteurDetail rapportEvenementMensuelSecteurDetail = (RapportEvenementMensuelSecteurDetail) o;
        return id != null && id.equals(rapportEvenementMensuelSecteurDetail.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

