package  ma.zyn.app.ws.dto.commun;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;




@JsonInclude(JsonInclude.Include.NON_NULL)
public class VesselDto  extends AuditBaseDto {

    private String code  ;
    private String label  ;
    private String style  ;
    private BigDecimal loa  ;
    private BigDecimal draft  ;
    private BigDecimal airDraft  ;




    public VesselDto(){
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








}
