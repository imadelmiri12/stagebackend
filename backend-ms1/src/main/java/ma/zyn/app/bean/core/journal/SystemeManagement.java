package ma.zyn.app.bean.core.journal;








import com.fasterxml.jackson.annotation.JsonInclude;
import ma.zyn.app.zynerator.bean.BaseEntity;
import jakarta.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "systeme_management")
@JsonInclude(JsonInclude.Include.NON_NULL)
@SequenceGenerator(name="systeme_management_seq",sequenceName="systeme_management_seq",allocationSize=1, initialValue = 1)
public class SystemeManagement  extends BaseEntity     {




    @Column(length = 500)
    private String code;

    @Column(length = 500)
    private String label;

    @Column(length = 500)
    private String style;



    public SystemeManagement(){
        super();
    }

    public SystemeManagement(Long id){
        this.id = id;
    }

    public SystemeManagement(Long id,String label){
        this.id = id;
        this.label = label ;
    }
    public SystemeManagement(String label){
        this.label = label ;
    }
    public SystemeManagement(String label,String code){
        this.label=label;
        this.code=code;
    }




    @Id
    @Column(name = "id")
    @GeneratedValue(strategy =  GenerationType.SEQUENCE,generator="systeme_management_seq")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SystemeManagement systemeManagement = (SystemeManagement) o;
        return id != null && id.equals(systemeManagement.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}

