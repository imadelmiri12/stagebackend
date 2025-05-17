package ma.zyn.app.bean.core.mensuel;






import ma.zyn.app.bean.core.commun.TypeEvenement;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "rapport_evenement_mensuel_type_evenement")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="rapport_evenement_mensuel_type_evenement_seq",sequenceName="rapport_evenement_mensuel_type_evenement_seq",allocationSize=1, initialValue = 1)
public class RapportEvenementMensuelTypeEvenement  extends BaseEntity     {




    private BigDecimal total = BigDecimal.ZERO;

    private BigDecimal totalCumul = BigDecimal.ZERO;

    private BigDecimal indicateurGlobalCumul = BigDecimal.ZERO;

    private RapportEvenementMensuel rapportEvenementMensuel ;
    private TypeEvenement typeEvenement ;

    public RapportEvenementMensuelTypeEvenement(BigDecimal total, TypeEvenement typeEvenement) {
        this.total = total;
        this.typeEvenement = typeEvenement;
    }

    public RapportEvenementMensuelTypeEvenement(){
        super();
    }

    public RapportEvenementMensuelTypeEvenement(Long id){
        this.id = id;
    }





    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="rapport_evenement_mensuel_type_evenement_seq")
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
    @JoinColumn(name = "type_evenement")
    public TypeEvenement getTypeEvenement(){
        return this.typeEvenement;
    }
    public void setTypeEvenement(TypeEvenement typeEvenement){
        this.typeEvenement = typeEvenement;
    }
    public BigDecimal getTotal(){
        return this.total;
    }
    public void setTotal(BigDecimal total){
        this.total = total;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RapportEvenementMensuelTypeEvenement rapportEvenementMensuelTypeEvenement = (RapportEvenementMensuelTypeEvenement) o;
        return id != null && id.equals(rapportEvenementMensuelTypeEvenement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

