package  ma.zyn.app.ws.dto.mensuel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zyn.app.ws.dto.commun.TypeEvenementDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RapportEvenementMensuelTypeEvenementDto  extends AuditBaseDto {

    private BigDecimal total  ;
    private BigDecimal totalCumul  ;
    private BigDecimal indicateurGlobalCumul  ;

    private RapportEvenementMensuelDto rapportEvenementMensuel ;
    private TypeEvenementDto typeEvenement ;



    public RapportEvenementMensuelTypeEvenementDto(){
        super();
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


    public RapportEvenementMensuelDto getRapportEvenementMensuel(){
        return this.rapportEvenementMensuel;
    }

    public void setRapportEvenementMensuel(RapportEvenementMensuelDto rapportEvenementMensuel){
        this.rapportEvenementMensuel = rapportEvenementMensuel;
    }
    public TypeEvenementDto getTypeEvenement(){
        return this.typeEvenement;
    }

    public void setTypeEvenement(TypeEvenementDto typeEvenement){
        this.typeEvenement = typeEvenement;
    }






}
