package  ma.zyn.app.ws.dto.journal;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.actor.CollaboratorDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class JournalAmelioarationDto  extends AuditBaseDto {

    private String date ;
    private Integer annee  = 0 ;
    private String dateButoir ;
    private String dateRealisation ;
    private String dateEvaluation ;
    private String dateCloture ;
    private BigDecimal avancement  ;
    private String descriptionEcrat  ;
    private String descriptionAction  ;
    private String commentaire  ;
    private String causeSuppose  ;

    private SystemeManagementDto systemeManagement ;
    private CategorieEcrateDto categorieEcrate ;
    private OrigineEcrateDto origineEcrate ;
    private TypeActionDto typeAction ;
    private CollaboratorDto collaborator ;
    private CritereEvaluationDto critereEvaluation ;
    private ResultatEvaluationDto resultatEvaluation ;



    public JournalAmelioarationDto(){
        super();
    }




    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDate(){
        return this.date;
    }
    public void setDate(String date){
        this.date = date;
    }


    public Integer getAnnee(){
        return this.annee;
    }
    public void setAnnee(Integer annee){
        this.annee = annee;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateButoir(){
        return this.dateButoir;
    }
    public void setDateButoir(String dateButoir){
        this.dateButoir = dateButoir;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateRealisation(){
        return this.dateRealisation;
    }
    public void setDateRealisation(String dateRealisation){
        this.dateRealisation = dateRealisation;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateEvaluation(){
        return this.dateEvaluation;
    }
    public void setDateEvaluation(String dateEvaluation){
        this.dateEvaluation = dateEvaluation;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateCloture(){
        return this.dateCloture;
    }
    public void setDateCloture(String dateCloture){
        this.dateCloture = dateCloture;
    }


    public BigDecimal getAvancement(){
        return this.avancement;
    }
    public void setAvancement(BigDecimal avancement){
        this.avancement = avancement;
    }


    public String getDescriptionEcrat(){
        return this.descriptionEcrat;
    }
    public void setDescriptionEcrat(String descriptionEcrat){
        this.descriptionEcrat = descriptionEcrat;
    }


    public String getDescriptionAction(){
        return this.descriptionAction;
    }
    public void setDescriptionAction(String descriptionAction){
        this.descriptionAction = descriptionAction;
    }


    public String getCommentaire(){
        return this.commentaire;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }


    public String getCauseSuppose(){
        return this.causeSuppose;
    }
    public void setCauseSuppose(String causeSuppose){
        this.causeSuppose = causeSuppose;
    }


    public SystemeManagementDto getSystemeManagement(){
        return this.systemeManagement;
    }

    public void setSystemeManagement(SystemeManagementDto systemeManagement){
        this.systemeManagement = systemeManagement;
    }
    public CategorieEcrateDto getCategorieEcrate(){
        return this.categorieEcrate;
    }

    public void setCategorieEcrate(CategorieEcrateDto categorieEcrate){
        this.categorieEcrate = categorieEcrate;
    }
    public OrigineEcrateDto getOrigineEcrate(){
        return this.origineEcrate;
    }

    public void setOrigineEcrate(OrigineEcrateDto origineEcrate){
        this.origineEcrate = origineEcrate;
    }
    public TypeActionDto getTypeAction(){
        return this.typeAction;
    }

    public void setTypeAction(TypeActionDto typeAction){
        this.typeAction = typeAction;
    }
    public CollaboratorDto getCollaborator(){
        return this.collaborator;
    }

    public void setCollaborator(CollaboratorDto collaborator){
        this.collaborator = collaborator;
    }
    public CritereEvaluationDto getCritereEvaluation(){
        return this.critereEvaluation;
    }

    public void setCritereEvaluation(CritereEvaluationDto critereEvaluation){
        this.critereEvaluation = critereEvaluation;
    }
    public ResultatEvaluationDto getResultatEvaluation(){
        return this.resultatEvaluation;
    }

    public void setResultatEvaluation(ResultatEvaluationDto resultatEvaluation){
        this.resultatEvaluation = resultatEvaluation;
    }






}
