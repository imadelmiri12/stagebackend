package ma.zyn.app.bean.core.commun;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;
import java.math.BigDecimal;

@Entity
@Table(name = "vessel")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="vessel_seq",sequenceName="vessel_seq",allocationSize=1, initialValue = 1)
public class Vessel  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String label;

    @Column(length = 500)
    private String style;

    private BigDecimal loa = BigDecimal.ZERO;

    private BigDecimal draft = BigDecimal.ZERO;

    private BigDecimal airDraft = BigDecimal.ZERO;



    public Vessel(){
        super();
    }

    public Vessel(Long id){
        this.id = id;
    }

    public Vessel(Long id,String label){
        this.id = id;
        this.label = label ;
    }
    public Vessel(String label){
        this.label = label ;
    }
    public Vessel(String label,String code){
        this.label=label;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="vessel_seq")
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
    public String getLabel(){
        return this.label;
    }
    public void setLabel(String label){
        this.label = label;
    }
    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }
    public BigDecimal getLoa(){
        return this.loa;
    }
    public void setLoa(BigDecimal loa){
        this.loa = loa;
    }
    public BigDecimal getDraft(){
        return this.draft;
    }
    public void setDraft(BigDecimal draft){
        this.draft = draft;
    }
    public BigDecimal getAirDraft(){
        return this.airDraft;
    }
    public void setAirDraft(BigDecimal airDraft){
        this.airDraft = airDraft;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vessel vessel = (Vessel) o;
        return id != null && id.equals(vessel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

