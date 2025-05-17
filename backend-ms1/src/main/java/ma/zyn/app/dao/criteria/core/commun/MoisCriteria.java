package  ma.zyn.app.dao.criteria.core.commun;


import ma.zyn.app.dao.criteria.core.indicateur.TrimestreCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;

public class MoisCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private String label;
    private String labelLike;
    private String style;
    private String styleLike;
    private String numero;
    private String numeroMin;
    private String numeroMax;

    private TrimestreCriteria trimestre ;
    private List<TrimestreCriteria> trimestres ;


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

    public String getNumero(){
        return this.numero;
    }
    public void setNumero(String numero){
        this.numero = numero;
    }   
    public String getNumeroMin(){
        return this.numeroMin;
    }
    public void setNumeroMin(String numeroMin){
        this.numeroMin = numeroMin;
    }
    public String getNumeroMax(){
        return this.numeroMax;
    }
    public void setNumeroMax(String numeroMax){
        this.numeroMax = numeroMax;
    }
      

    public TrimestreCriteria getTrimestre(){
        return this.trimestre;
    }

    public void setTrimestre(TrimestreCriteria trimestre){
        this.trimestre = trimestre;
    }
    public List<TrimestreCriteria> getTrimestres(){
        return this.trimestres;
    }

    public void setTrimestres(List<TrimestreCriteria> trimestres){
        this.trimestres = trimestres;
    }
}
