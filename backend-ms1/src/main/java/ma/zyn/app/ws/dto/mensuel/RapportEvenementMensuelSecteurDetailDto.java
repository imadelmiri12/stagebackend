package  ma.zyn.app.ws.dto.mensuel;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;


import ma.zyn.app.ws.dto.commun.TypeEvenementDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RapportEvenementMensuelSecteurDetailDto  extends AuditBaseDto {

    private BigDecimal total  ;
    private BigDecimal indicateurGlobal  ;

    private RapportEvenementMensuelSecteurDto rapportEvenementMensuelSecteur ;
    private TypeEvenementDto typeEvenement ;



    public RapportEvenementMensuelSecteurDetailDto(){
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


    public RapportEvenementMensuelSecteurDto getRapportEvenementMensuelSecteur(){
        return this.rapportEvenementMensuelSecteur;
    }

    public void setRapportEvenementMensuelSecteur(RapportEvenementMensuelSecteurDto rapportEvenementMensuelSecteur){
        this.rapportEvenementMensuelSecteur = rapportEvenementMensuelSecteur;
    }
    public TypeEvenementDto getTypeEvenement(){
        return this.typeEvenement;
    }

    public void setTypeEvenement(TypeEvenementDto typeEvenement){
        this.typeEvenement = typeEvenement;
    }






}
