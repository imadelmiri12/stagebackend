package ma.zyn.app.bean.core.commun;






import ma.zyn.app.bean.core.indicateur.Trimestre;


import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "mois")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="mois_seq",sequenceName="mois_seq",allocationSize=1, initialValue = 1)
public class Mois  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String label;

    @Column(length = 500)
    private String style;

    private Integer numero = 0;

    private Trimestre trimestre ;


    public Mois(){
        super();
    }

    public Mois(Long id){
        this.id = id;
    }

    public Mois(Long id,String label){
        this.id = id;
        this.label = label ;
    }
    public Mois(String label){
        this.label = label ;
    }
    public Mois(String label,String code){
        this.label=label;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="mois_seq")
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
    public Integer getNumero(){
        return this.numero;
    }
    public void setNumero(Integer numero){
        this.numero = numero;
    }
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trimestre")
    public Trimestre getTrimestre(){
        return this.trimestre;
    }
    public void setTrimestre(Trimestre trimestre){
        this.trimestre = trimestre;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mois mois = (Mois) o;
        return id != null && id.equals(mois.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

