package  ma.zyn.app.dao.criteria.core.journal;


import ma.zyn.app.dao.criteria.core.actor.CollaboratorCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class JournalAmelioarationCriteria extends  BaseCriteria  {

    private LocalDateTime date;
    private LocalDateTime dateFrom;
    private LocalDateTime dateTo;
    private String annee;
    private String anneeMin;
    private String anneeMax;
    private LocalDateTime dateButoir;
    private LocalDateTime dateButoirFrom;
    private LocalDateTime dateButoirTo;
    private LocalDateTime dateRealisation;
    private LocalDateTime dateRealisationFrom;
    private LocalDateTime dateRealisationTo;
    private LocalDateTime dateEvaluation;
    private LocalDateTime dateEvaluationFrom;
    private LocalDateTime dateEvaluationTo;
    private LocalDateTime dateCloture;
    private LocalDateTime dateClotureFrom;
    private LocalDateTime dateClotureTo;
    private String avancement;
    private String avancementMin;
    private String avancementMax;
    private String descriptionEcrat;
    private String descriptionEcratLike;
    private String descriptionAction;
    private String descriptionActionLike;
    private String commentaire;
    private String commentaireLike;
    private String causeSuppose;
    private String causeSupposeLike;

    private SystemeManagementCriteria systemeManagement ;
    private List<SystemeManagementCriteria> systemeManagements ;
    private CategorieEcrateCriteria categorieEcrate ;
    private List<CategorieEcrateCriteria> categorieEcrates ;
    private OrigineEcrateCriteria origineEcrate ;
    private List<OrigineEcrateCriteria> origineEcrates ;
    private TypeActionCriteria typeAction ;
    private List<TypeActionCriteria> typeActions ;
    private CollaboratorCriteria collaborator ;
    private List<CollaboratorCriteria> collaborators ;
    private CritereEvaluationCriteria critereEvaluation ;
    private List<CritereEvaluationCriteria> critereEvaluations ;
    private ResultatEvaluationCriteria resultatEvaluation ;
    private List<ResultatEvaluationCriteria> resultatEvaluations ;


    public LocalDateTime getDate(){
        return this.date;
    }
    public void setDate(LocalDateTime date){
        this.date = date;
    }
    public LocalDateTime getDateFrom(){
        return this.dateFrom;
    }
    public void setDateFrom(LocalDateTime dateFrom){
        this.dateFrom = dateFrom;
    }
    public LocalDateTime getDateTo(){
        return this.dateTo;
    }
    public void setDateTo(LocalDateTime dateTo){
        this.dateTo = dateTo;
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
      
    public LocalDateTime getDateButoir(){
        return this.dateButoir;
    }
    public void setDateButoir(LocalDateTime dateButoir){
        this.dateButoir = dateButoir;
    }
    public LocalDateTime getDateButoirFrom(){
        return this.dateButoirFrom;
    }
    public void setDateButoirFrom(LocalDateTime dateButoirFrom){
        this.dateButoirFrom = dateButoirFrom;
    }
    public LocalDateTime getDateButoirTo(){
        return this.dateButoirTo;
    }
    public void setDateButoirTo(LocalDateTime dateButoirTo){
        this.dateButoirTo = dateButoirTo;
    }
    public LocalDateTime getDateRealisation(){
        return this.dateRealisation;
    }
    public void setDateRealisation(LocalDateTime dateRealisation){
        this.dateRealisation = dateRealisation;
    }
    public LocalDateTime getDateRealisationFrom(){
        return this.dateRealisationFrom;
    }
    public void setDateRealisationFrom(LocalDateTime dateRealisationFrom){
        this.dateRealisationFrom = dateRealisationFrom;
    }
    public LocalDateTime getDateRealisationTo(){
        return this.dateRealisationTo;
    }
    public void setDateRealisationTo(LocalDateTime dateRealisationTo){
        this.dateRealisationTo = dateRealisationTo;
    }
    public LocalDateTime getDateEvaluation(){
        return this.dateEvaluation;
    }
    public void setDateEvaluation(LocalDateTime dateEvaluation){
        this.dateEvaluation = dateEvaluation;
    }
    public LocalDateTime getDateEvaluationFrom(){
        return this.dateEvaluationFrom;
    }
    public void setDateEvaluationFrom(LocalDateTime dateEvaluationFrom){
        this.dateEvaluationFrom = dateEvaluationFrom;
    }
    public LocalDateTime getDateEvaluationTo(){
        return this.dateEvaluationTo;
    }
    public void setDateEvaluationTo(LocalDateTime dateEvaluationTo){
        this.dateEvaluationTo = dateEvaluationTo;
    }
    public LocalDateTime getDateCloture(){
        return this.dateCloture;
    }
    public void setDateCloture(LocalDateTime dateCloture){
        this.dateCloture = dateCloture;
    }
    public LocalDateTime getDateClotureFrom(){
        return this.dateClotureFrom;
    }
    public void setDateClotureFrom(LocalDateTime dateClotureFrom){
        this.dateClotureFrom = dateClotureFrom;
    }
    public LocalDateTime getDateClotureTo(){
        return this.dateClotureTo;
    }
    public void setDateClotureTo(LocalDateTime dateClotureTo){
        this.dateClotureTo = dateClotureTo;
    }
    public String getAvancement(){
        return this.avancement;
    }
    public void setAvancement(String avancement){
        this.avancement = avancement;
    }   
    public String getAvancementMin(){
        return this.avancementMin;
    }
    public void setAvancementMin(String avancementMin){
        this.avancementMin = avancementMin;
    }
    public String getAvancementMax(){
        return this.avancementMax;
    }
    public void setAvancementMax(String avancementMax){
        this.avancementMax = avancementMax;
    }
      
    public String getDescriptionEcrat(){
        return this.descriptionEcrat;
    }
    public void setDescriptionEcrat(String descriptionEcrat){
        this.descriptionEcrat = descriptionEcrat;
    }
    public String getDescriptionEcratLike(){
        return this.descriptionEcratLike;
    }
    public void setDescriptionEcratLike(String descriptionEcratLike){
        this.descriptionEcratLike = descriptionEcratLike;
    }

    public String getDescriptionAction(){
        return this.descriptionAction;
    }
    public void setDescriptionAction(String descriptionAction){
        this.descriptionAction = descriptionAction;
    }
    public String getDescriptionActionLike(){
        return this.descriptionActionLike;
    }
    public void setDescriptionActionLike(String descriptionActionLike){
        this.descriptionActionLike = descriptionActionLike;
    }

    public String getCommentaire(){
        return this.commentaire;
    }
    public void setCommentaire(String commentaire){
        this.commentaire = commentaire;
    }
    public String getCommentaireLike(){
        return this.commentaireLike;
    }
    public void setCommentaireLike(String commentaireLike){
        this.commentaireLike = commentaireLike;
    }

    public String getCauseSuppose(){
        return this.causeSuppose;
    }
    public void setCauseSuppose(String causeSuppose){
        this.causeSuppose = causeSuppose;
    }
    public String getCauseSupposeLike(){
        return this.causeSupposeLike;
    }
    public void setCauseSupposeLike(String causeSupposeLike){
        this.causeSupposeLike = causeSupposeLike;
    }


    public SystemeManagementCriteria getSystemeManagement(){
        return this.systemeManagement;
    }

    public void setSystemeManagement(SystemeManagementCriteria systemeManagement){
        this.systemeManagement = systemeManagement;
    }
    public List<SystemeManagementCriteria> getSystemeManagements(){
        return this.systemeManagements;
    }

    public void setSystemeManagements(List<SystemeManagementCriteria> systemeManagements){
        this.systemeManagements = systemeManagements;
    }
    public CategorieEcrateCriteria getCategorieEcrate(){
        return this.categorieEcrate;
    }

    public void setCategorieEcrate(CategorieEcrateCriteria categorieEcrate){
        this.categorieEcrate = categorieEcrate;
    }
    public List<CategorieEcrateCriteria> getCategorieEcrates(){
        return this.categorieEcrates;
    }

    public void setCategorieEcrates(List<CategorieEcrateCriteria> categorieEcrates){
        this.categorieEcrates = categorieEcrates;
    }
    public OrigineEcrateCriteria getOrigineEcrate(){
        return this.origineEcrate;
    }

    public void setOrigineEcrate(OrigineEcrateCriteria origineEcrate){
        this.origineEcrate = origineEcrate;
    }
    public List<OrigineEcrateCriteria> getOrigineEcrates(){
        return this.origineEcrates;
    }

    public void setOrigineEcrates(List<OrigineEcrateCriteria> origineEcrates){
        this.origineEcrates = origineEcrates;
    }
    public TypeActionCriteria getTypeAction(){
        return this.typeAction;
    }

    public void setTypeAction(TypeActionCriteria typeAction){
        this.typeAction = typeAction;
    }
    public List<TypeActionCriteria> getTypeActions(){
        return this.typeActions;
    }

    public void setTypeActions(List<TypeActionCriteria> typeActions){
        this.typeActions = typeActions;
    }
    public CollaboratorCriteria getCollaborator(){
        return this.collaborator;
    }

    public void setCollaborator(CollaboratorCriteria collaborator){
        this.collaborator = collaborator;
    }
    public List<CollaboratorCriteria> getCollaborators(){
        return this.collaborators;
    }

    public void setCollaborators(List<CollaboratorCriteria> collaborators){
        this.collaborators = collaborators;
    }
    public CritereEvaluationCriteria getCritereEvaluation(){
        return this.critereEvaluation;
    }

    public void setCritereEvaluation(CritereEvaluationCriteria critereEvaluation){
        this.critereEvaluation = critereEvaluation;
    }
    public List<CritereEvaluationCriteria> getCritereEvaluations(){
        return this.critereEvaluations;
    }

    public void setCritereEvaluations(List<CritereEvaluationCriteria> critereEvaluations){
        this.critereEvaluations = critereEvaluations;
    }
    public ResultatEvaluationCriteria getResultatEvaluation(){
        return this.resultatEvaluation;
    }

    public void setResultatEvaluation(ResultatEvaluationCriteria resultatEvaluation){
        this.resultatEvaluation = resultatEvaluation;
    }
    public List<ResultatEvaluationCriteria> getResultatEvaluations(){
        return this.resultatEvaluations;
    }

    public void setResultatEvaluations(List<ResultatEvaluationCriteria> resultatEvaluations){
        this.resultatEvaluations = resultatEvaluations;
    }
}
