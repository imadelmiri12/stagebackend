package ma.zyn.app.bean.core.commun;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "type_vessel")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="type_vessel_seq",sequenceName="type_vessel_seq",allocationSize=1, initialValue = 1)
public class TypeVessel  extends BaseEntity     {




    private Integer indexation = 0;

    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String label;

    @Column(length = 500)
    private String style;

    @Column(length = 500)
    private String color;



    public TypeVessel(){
        super();
    }

    public TypeVessel(Long id){
        this.id = id;
    }

    public TypeVessel(Long id,String label){
        this.id = id;
        this.label = label ;
    }
    public TypeVessel(String label){
        this.label = label ;
    }
    public TypeVessel(String label,String code){
        this.label=label;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="type_vessel_seq")
      @Override
    public Long getId(){
        return this.id;
    }
        @Override
    public void setId(Long id){
        this.id = id;
    }
    public Integer getIndexation(){
        return this.indexation;
    }
    public void setIndexation(Integer indexation){
        this.indexation = indexation;
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
    public String getColor(){
        return this.color;
    }
    public void setColor(String color){
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TypeVessel typeVessel = (TypeVessel) o;
        return id != null && id.equals(typeVessel.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

