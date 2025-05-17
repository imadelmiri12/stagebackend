package ma.zyn.app.bean.core.mensuel;

import java.util.List;

import java.time.LocalDateTime;


import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;


import ma.zyn.app.bean.core.commun.Secteur;
import ma.zyn.app.bean.core.commun.TypeEvenement;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "rapport_evenement_mensuel")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="rapport_evenement_mensuel_seq",sequenceName="rapport_evenement_mensuel_seq",allocationSize=1, initialValue = 1)
public class RapportEvenementMensuel  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    private LocalDateTime dateEmission ;

    private String description;

    private Integer mois = 0;

    private Integer annee = 0;

    private Integer nombreMouvement = 0;

    private BigDecimal total = BigDecimal.ZERO;

    private BigDecimal indicateurGlobal = BigDecimal.ZERO;

    private Integer nombreMouvementCumul = 0;

    private BigDecimal totalCumul = BigDecimal.ZERO;

    private BigDecimal indicateurGlobalCumul = BigDecimal.ZERO;


    private List<RapportEvenementMensuelSecteur> rapportEvenementMensuelSecteurs ;
    private List<RapportEvenementMensuelTypeEvenement> rapportEvenementMensuelTypeEvenements ;

    public RapportEvenementMensuel(){
        super();
    }

    public RapportEvenementMensuel(Long id){
        this.id = id;
    }

    public RapportEvenementMensuel(Long id,String code){
        this.id = id;
        this.code = code ;
    }
    public RapportEvenementMensuel(String code){
        this.code = code ;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="rapport_evenement_mensuel_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public LocalDateTime getDateEmission(){
        return this.dateEmission;
    }
    public void setDateEmission(LocalDateTime dateEmission){
        this.dateEmission = dateEmission;
    }
      @Column(columnDefinition="TEXT")
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public Integer getMois(){
        return this.mois;
    }
    public void setMois(Integer mois){
        this.mois = mois;
    }
    public Integer getAnnee(){
        return this.annee;
    }
    public void setAnnee(Integer annee){
        this.annee = annee;
    }
    public Integer getNombreMouvement(){
        return this.nombreMouvement;
    }
    public void setNombreMouvement(Integer nombreMouvement){
        this.nombreMouvement = nombreMouvement;
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
    public Integer getNombreMouvementCumul(){
        return this.nombreMouvementCumul;
    }
    public void setNombreMouvementCumul(Integer nombreMouvementCumul){
        this.nombreMouvementCumul = nombreMouvementCumul;
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
    @OneToMany(mappedBy = "rapportEvenementMensuel")
    public List<RapportEvenementMensuelSecteur> getRapportEvenementMensuelSecteurs(){
        return this.rapportEvenementMensuelSecteurs;
    }

    public void setRapportEvenementMensuelSecteurs(List<RapportEvenementMensuelSecteur> rapportEvenementMensuelSecteurs){
        this.rapportEvenementMensuelSecteurs = rapportEvenementMensuelSecteurs;
    }
    @OneToMany(mappedBy = "rapportEvenementMensuel")
    public List<RapportEvenementMensuelTypeEvenement> getRapportEvenementMensuelTypeEvenements(){
        return this.rapportEvenementMensuelTypeEvenements;
    }

    public void setRapportEvenementMensuelTypeEvenements(List<RapportEvenementMensuelTypeEvenement> rapportEvenementMensuelTypeEvenements){
        this.rapportEvenementMensuelTypeEvenements = rapportEvenementMensuelTypeEvenements;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RapportEvenementMensuel rapportEvenementMensuel = (RapportEvenementMensuel) o;
        return id != null && id.equals(rapportEvenementMensuel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

