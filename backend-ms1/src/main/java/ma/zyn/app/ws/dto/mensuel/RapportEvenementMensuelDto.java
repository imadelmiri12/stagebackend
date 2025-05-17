package  ma.zyn.app.ws.dto.mensuel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.commun.SecteurDto;
import ma.zyn.app.ws.dto.commun.TypeEvenementDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RapportEvenementMensuelDto  extends AuditBaseDto {

    private String code  ;
    private String dateEmission ;
    private String description  ;
    private Integer mois  = 0 ;
    private Integer annee  = 0 ;
    private Integer nombreMouvement  = 0 ;
    private BigDecimal total  ;
    private BigDecimal indicateurGlobal  ;
    private Integer nombreMouvementCumul  = 0 ;
    private BigDecimal totalCumul  ;
    private BigDecimal indicateurGlobalCumul  ;


    private List<RapportEvenementMensuelSecteurDto> rapportEvenementMensuelSecteurs ;
    private List<RapportEvenementMensuelTypeEvenementDto> rapportEvenementMensuelTypeEvenements ;


    public RapportEvenementMensuelDto(){
        super();
    }




    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateEmission(){
        return this.dateEmission;
    }
    public void setDateEmission(String dateEmission){
        this.dateEmission = dateEmission;
    }


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





    public List<RapportEvenementMensuelSecteurDto> getRapportEvenementMensuelSecteurs(){
        return this.rapportEvenementMensuelSecteurs;
    }

    public void setRapportEvenementMensuelSecteurs(List<RapportEvenementMensuelSecteurDto> rapportEvenementMensuelSecteurs){
        this.rapportEvenementMensuelSecteurs = rapportEvenementMensuelSecteurs;
    }
    public List<RapportEvenementMensuelTypeEvenementDto> getRapportEvenementMensuelTypeEvenements(){
        return this.rapportEvenementMensuelTypeEvenements;
    }

    public void setRapportEvenementMensuelTypeEvenements(List<RapportEvenementMensuelTypeEvenementDto> rapportEvenementMensuelTypeEvenements){
        this.rapportEvenementMensuelTypeEvenements = rapportEvenementMensuelTypeEvenements;
    }



}
