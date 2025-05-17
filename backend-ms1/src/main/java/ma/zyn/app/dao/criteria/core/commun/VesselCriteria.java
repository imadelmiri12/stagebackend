package  ma.zyn.app.dao.criteria.core.commun;



import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class VesselCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String label;
    private String labelLike;
    private String style;
    private String styleLike;
    private String loa;
    private String loaMin;
    private String loaMax;
    private String draft;
    private String draftMin;
    private String draftMax;
    private String airDraft;
    private String airDraftMin;
    private String airDraftMax;



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

    public String getLabel(){
        return this.label;
    }
    public void setLabel(String label){
        this.label = label;
    }
    public String getLabelLike(){
        return this.labelLike;
    }
    public void setLabelLike(String labelLike){
        this.labelLike = labelLike;
    }

    public String getStyle(){
        return this.style;
    }
    public void setStyle(String style){
        this.style = style;
    }
    public String getStyleLike(){
        return this.styleLike;
    }
    public void setStyleLike(String styleLike){
        this.styleLike = styleLike;
    }

    public String getLoa(){
        return this.loa;
    }
    public void setLoa(String loa){
        this.loa = loa;
    }   
    public String getLoaMin(){
        return this.loaMin;
    }
    public void setLoaMin(String loaMin){
        this.loaMin = loaMin;
    }
    public String getLoaMax(){
        return this.loaMax;
    }
    public void setLoaMax(String loaMax){
        this.loaMax = loaMax;
    }
      
    public String getDraft(){
        return this.draft;
    }
    public void setDraft(String draft){
        this.draft = draft;
    }   
    public String getDraftMin(){
        return this.draftMin;
    }
    public void setDraftMin(String draftMin){
        this.draftMin = draftMin;
    }
    public String getDraftMax(){
        return this.draftMax;
    }
    public void setDraftMax(String draftMax){
        this.draftMax = draftMax;
    }
      
    public String getAirDraft(){
        return this.airDraft;
    }
    public void setAirDraft(String airDraft){
        this.airDraft = airDraft;
    }   
    public String getAirDraftMin(){
        return this.airDraftMin;
    }
    public void setAirDraftMin(String airDraftMin){
        this.airDraftMin = airDraftMin;
    }
    public String getAirDraftMax(){
        return this.airDraftMax;
    }
    public void setAirDraftMax(String airDraftMax){
        this.airDraftMax = airDraftMax;
    }
      

}
