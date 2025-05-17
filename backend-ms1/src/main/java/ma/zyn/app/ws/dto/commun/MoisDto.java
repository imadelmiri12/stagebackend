package  ma.zyn.app.ws.dto.commun;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;



import ma.zyn.app.ws.dto.indicateur.TrimestreDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class MoisDto  extends AuditBaseDto {

    private String code  ;
    private String label  ;
    private String style  ;
    private Integer numero  = 0 ;

    private TrimestreDto trimestre ;



    public MoisDto(){
        super();
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


    public TrimestreDto getTrimestre(){
        return this.trimestre;
    }

    public void setTrimestre(TrimestreDto trimestre){
        this.trimestre = trimestre;
    }






}
