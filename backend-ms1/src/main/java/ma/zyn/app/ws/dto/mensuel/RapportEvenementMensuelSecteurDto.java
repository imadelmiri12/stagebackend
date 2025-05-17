package  ma.zyn.app.ws.dto.mensuel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.commun.SecteurDto;
import ma.zyn.app.ws.dto.commun.TypeEvenementDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RapportEvenementMensuelSecteurDto  extends AuditBaseDto {

    private BigDecimal total  ;
    private BigDecimal indicateurGlobal  ;
    private BigDecimal totalCumul  ;
    private BigDecimal indicateurGlobalCumul  ;

    private RapportEvenementMensuelDto rapportEvenementMensuel ;
    private SecteurDto secteur ;

    private List<RapportEvenementMensuelSecteurDetailDto> rapportEvenementMensuelSecteurDetails ;


    public RapportEvenementMensuelSecteurDto(){
        super();
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
    public SecteurDto getSecteur(){
        return this.secteur;
    }

    public void setSecteur(SecteurDto secteur){
        this.secteur = secteur;
    }



    public List<RapportEvenementMensuelSecteurDetailDto> getRapportEvenementMensuelSecteurDetails(){
        return this.rapportEvenementMensuelSecteurDetails;
    }

    public void setRapportEvenementMensuelSecteurDetails(List<RapportEvenementMensuelSecteurDetailDto> rapportEvenementMensuelSecteurDetails){
        this.rapportEvenementMensuelSecteurDetails = rapportEvenementMensuelSecteurDetails;
    }



}
