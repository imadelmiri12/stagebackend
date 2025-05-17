package  ma.zyn.app.dao.criteria.core.mensuel;


import ma.zyn.app.dao.criteria.core.commun.TypeEvenementCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class RapportEvenementMensuelTypeEvenementCriteria extends  BaseCriteria  {

    private String total;
    private String totalMin;
    private String totalMax;
    private String totalCumul;
    private String totalCumulMin;
    private String totalCumulMax;
    private String indicateurGlobalCumul;
    private String indicateurGlobalCumulMin;
    private String indicateurGlobalCumulMax;

    private RapportEvenementMensuelCriteria rapportEvenementMensuel ;
    private List<RapportEvenementMensuelCriteria> rapportEvenementMensuels ;
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
      
    public String getTotalCumul(){
        return this.totalCumul;
    }
    public void setTotalCumul(String totalCumul){
        this.totalCumul = totalCumul;
    }   
    public String getTotalCumulMin(){
        return this.totalCumulMin;
    }
    public void setTotalCumulMin(String totalCumulMin){
        this.totalCumulMin = totalCumulMin;
    }
    public String getTotalCumulMax(){
        return this.totalCumulMax;
    }
    public void setTotalCumulMax(String totalCumulMax){
        this.totalCumulMax = totalCumulMax;
    }
      
    public String getIndicateurGlobalCumul(){
        return this.indicateurGlobalCumul;
    }
    public void setIndicateurGlobalCumul(String indicateurGlobalCumul){
        this.indicateurGlobalCumul = indicateurGlobalCumul;
    }   
    public String getIndicateurGlobalCumulMin(){
        return this.indicateurGlobalCumulMin;
    }
    public void setIndicateurGlobalCumulMin(String indicateurGlobalCumulMin){
        this.indicateurGlobalCumulMin = indicateurGlobalCumulMin;
    }
    public String getIndicateurGlobalCumulMax(){
        return this.indicateurGlobalCumulMax;
    }
    public void setIndicateurGlobalCumulMax(String indicateurGlobalCumulMax){
        this.indicateurGlobalCumulMax = indicateurGlobalCumulMax;
    }
      

    public RapportEvenementMensuelCriteria getRapportEvenementMensuel(){
        return this.rapportEvenementMensuel;
    }

    public void setRapportEvenementMensuel(RapportEvenementMensuelCriteria rapportEvenementMensuel){
        this.rapportEvenementMensuel = rapportEvenementMensuel;
    }
    public List<RapportEvenementMensuelCriteria> getRapportEvenementMensuels(){
        return this.rapportEvenementMensuels;
    }

    public void setRapportEvenementMensuels(List<RapportEvenementMensuelCriteria> rapportEvenementMensuels){
        this.rapportEvenementMensuels = rapportEvenementMensuels;
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
