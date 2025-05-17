package  ma.zyn.app.dao.criteria.core.mensuel;


import ma.zyn.app.dao.criteria.core.commun.TypeEvenementCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class RapportEvenementMensuelSecteurDetailCriteria extends  BaseCriteria  {

    private String total;
    private String totalMin;
    private String totalMax;
    private String indicateurGlobal;
    private String indicateurGlobalMin;
    private String indicateurGlobalMax;

    private RapportEvenementMensuelSecteurCriteria rapportEvenementMensuelSecteur ;
    private List<RapportEvenementMensuelSecteurCriteria> rapportEvenementMensuelSecteurs ;
    private TypeEvenementCriteria typeEvenement ;
    private List<TypeEvenementCriteria> typeEvenements ;


    public String getTotal(){
        return this.total;
    }
    public void setTotal(String total){
        this.total = total;
    }   
    public String getTotalMin(){
        return this.totalMin;
    }
    public void setTotalMin(String totalMin){
        this.totalMin = totalMin;
    }
    public String getTotalMax(){
        return this.totalMax;
    }
    public void setTotalMax(String totalMax){
        this.totalMax = totalMax;
    }
      
    public String getIndicateurGlobal(){
        return this.indicateurGlobal;
    }
    public void setIndicateurGlobal(String indicateurGlobal){
        this.indicateurGlobal = indicateurGlobal;
    }   
    public String getIndicateurGlobalMin(){
        return this.indicateurGlobalMin;
    }
    public void setIndicateurGlobalMin(String indicateurGlobalMin){
        this.indicateurGlobalMin = indicateurGlobalMin;
    }
    public String getIndicateurGlobalMax(){
        return this.indicateurGlobalMax;
    }
    public void setIndicateurGlobalMax(String indicateurGlobalMax){
        this.indicateurGlobalMax = indicateurGlobalMax;
    }
      

    public RapportEvenementMensuelSecteurCriteria getRapportEvenementMensuelSecteur(){
        return this.rapportEvenementMensuelSecteur;
    }

    public void setRapportEvenementMensuelSecteur(RapportEvenementMensuelSecteurCriteria rapportEvenementMensuelSecteur){
        this.rapportEvenementMensuelSecteur = rapportEvenementMensuelSecteur;
    }
    public List<RapportEvenementMensuelSecteurCriteria> getRapportEvenementMensuelSecteurs(){
        return this.rapportEvenementMensuelSecteurs;
    }

    public void setRapportEvenementMensuelSecteurs(List<RapportEvenementMensuelSecteurCriteria> rapportEvenementMensuelSecteurs){
        this.rapportEvenementMensuelSecteurs = rapportEvenementMensuelSecteurs;
    }
    public TypeEvenementCriteria getTypeEvenement(){
        return this.typeEvenement;
    }

    public void setTypeEvenement(TypeEvenementCriteria typeEvenement){
        this.typeEvenement = typeEvenement;
    }
    public List<TypeEvenementCriteria> getTypeEvenements(){
        return this.typeEvenements;
    }

    public void setTypeEvenements(List<TypeEvenementCriteria> typeEvenements){
        this.typeEvenements = typeEvenements;
    }
}
