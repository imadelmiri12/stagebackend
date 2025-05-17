package  ma.zyn.app.dao.criteria.core.mensuel;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class RapportEvenementMensuelCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private LocalDateTime dateEmission;
    private LocalDateTime dateEmissionFrom;
    private LocalDateTime dateEmissionTo;
    private String description;
    private String descriptionLike;
    private String mois;
    private String moisMin;
    private String moisMax;
    private String annee;
    private String anneeMin;
    private String anneeMax;
    private String nombreMouvement;
    private String nombreMouvementMin;
    private String nombreMouvementMax;
    private String total;
    private String totalMin;
    private String totalMax;
    private String indicateurGlobal;
    private String indicateurGlobalMin;
    private String indicateurGlobalMax;
    private String nombreMouvementCumul;
    private String nombreMouvementCumulMin;
    private String nombreMouvementCumulMax;
    private String totalCumul;
    private String totalCumulMin;
    private String totalCumulMax;
    private String indicateurGlobalCumul;
    private String indicateurGlobalCumulMin;
    private String indicateurGlobalCumulMax;



    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }
    public String getCodeLike(){
        return this.codeLike;
    }
    public void setCodeLike(String codeLike){
        this.codeLike = codeLike;
    }

    public LocalDateTime getDateEmission(){
        return this.dateEmission;
    }
    public void setDateEmission(LocalDateTime dateEmission){
        this.dateEmission = dateEmission;
    }
    public LocalDateTime getDateEmissionFrom(){
        return this.dateEmissionFrom;
    }
    public void setDateEmissionFrom(LocalDateTime dateEmissionFrom){
        this.dateEmissionFrom = dateEmissionFrom;
    }
    public LocalDateTime getDateEmissionTo(){
        return this.dateEmissionTo;
    }
    public void setDateEmissionTo(LocalDateTime dateEmissionTo){
        this.dateEmissionTo = dateEmissionTo;
    }
    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }
    public String getDescriptionLike(){
        return this.descriptionLike;
    }
    public void setDescriptionLike(String descriptionLike){
        this.descriptionLike = descriptionLike;
    }

    public String getMois(){
        return this.mois;
    }
    public void setMois(String mois){
        this.mois = mois;
    }   
    public String getMoisMin(){
        return this.moisMin;
    }
    public void setMoisMin(String moisMin){
        this.moisMin = moisMin;
    }
    public String getMoisMax(){
        return this.moisMax;
    }
    public void setMoisMax(String moisMax){
        this.moisMax = moisMax;
    }
      
    public String getAnnee(){
        return this.annee;
    }
    public void setAnnee(String annee){
        this.annee = annee;
    }   
    public String getAnneeMin(){
        return this.anneeMin;
    }
    public void setAnneeMin(String anneeMin){
        this.anneeMin = anneeMin;
    }
    public String getAnneeMax(){
        return this.anneeMax;
    }
    public void setAnneeMax(String anneeMax){
        this.anneeMax = anneeMax;
    }
      
    public String getNombreMouvement(){
        return this.nombreMouvement;
    }
    public void setNombreMouvement(String nombreMouvement){
        this.nombreMouvement = nombreMouvement;
    }   
    public String getNombreMouvementMin(){
        return this.nombreMouvementMin;
    }
    public void setNombreMouvementMin(String nombreMouvementMin){
        this.nombreMouvementMin = nombreMouvementMin;
    }
    public String getNombreMouvementMax(){
        return this.nombreMouvementMax;
    }
    public void setNombreMouvementMax(String nombreMouvementMax){
        this.nombreMouvementMax = nombreMouvementMax;
    }
      
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
      
    public String getNombreMouvementCumul(){
        return this.nombreMouvementCumul;
    }
    public void setNombreMouvementCumul(String nombreMouvementCumul){
        this.nombreMouvementCumul = nombreMouvementCumul;
    }   
    public String getNombreMouvementCumulMin(){
        return this.nombreMouvementCumulMin;
    }
    public void setNombreMouvementCumulMin(String nombreMouvementCumulMin){
        this.nombreMouvementCumulMin = nombreMouvementCumulMin;
    }
    public String getNombreMouvementCumulMax(){
        return this.nombreMouvementCumulMax;
    }
    public void setNombreMouvementCumulMax(String nombreMouvementCumulMax){
        this.nombreMouvementCumulMax = nombreMouvementCumulMax;
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
      

}
