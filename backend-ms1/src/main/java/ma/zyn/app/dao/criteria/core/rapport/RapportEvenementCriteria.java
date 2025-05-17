package  ma.zyn.app.dao.criteria.core.rapport;


import ma.zyn.app.dao.criteria.core.actor.CollaboratorCriteria;
import ma.zyn.app.dao.criteria.core.commun.TypeRapportEvenementCriteria;
import ma.zyn.app.dao.criteria.core.commun.VesselCriteria;
import ma.zyn.app.dao.criteria.core.commun.PortCriteria;
import ma.zyn.app.dao.criteria.core.commun.SecteurCriteria;
import ma.zyn.app.dao.criteria.core.commun.TypeEvenementCriteria;
import ma.zyn.app.dao.criteria.core.journal.JournalAmelioarationCriteria;

import ma.zyn.app.zynerator.criteria.BaseCriteria;

import java.util.List;
import java.time.LocalDateTime;
import java.time.LocalDate;

public class RapportEvenementCriteria extends  BaseCriteria  {

    private String code;
    private String codeLike;
    private LocalDateTime dateEvenement;
    private LocalDateTime dateEvenementFrom;
    private LocalDateTime dateEvenementTo;
    private LocalDateTime dateSoumission;
    private LocalDateTime dateSoumissionFrom;
    private LocalDateTime dateSoumissionTo;
    private String attachments;
    private String attachmentsLike;
    private String description;
    private String descriptionLike;
    private String recommendation;
    private String recommendationLike;
    private String directivePmjChef;
    private String directivePmjChefLike;
    private String mois;
    private String moisMin;
    private String moisMax;
    private String annee;
    private String anneeMin;
    private String anneeMax;
    private String windDirection;
    private String windDirectionMin;
    private String windDirectionMax;
    private String windForce;
    private String windForceMin;
    private String windForceMax;
    private String currentDirection;
    private String currentDirectionMin;
    private String currentDirectionMax;
    private String currentForce;
    private String currentForceMin;
    private String currentForceMax;
    private String swellHeigth;
    private String swellHeigthMin;
    private String swellHeigthMax;
    private String swellDirection;
    private String swellDirectionMin;
    private String swellDirectionMax;
    private Boolean fonde;

    private PortCriteria port ;
    private List<PortCriteria> ports ;
    private TypeEvenementCriteria typeEvenement ;
    private List<TypeEvenementCriteria> typeEvenements ;
    private CollaboratorCriteria collaborator ;
    private List<CollaboratorCriteria> collaborators ;
    private VesselCriteria vessel ;
    private List<VesselCriteria> vessels ;
    private TypeRapportEvenementCriteria typeRapportEvenement ;
    private List<TypeRapportEvenementCriteria> typeRapportEvenements ;
    private SecteurCriteria secteur ;
    private List<SecteurCriteria> secteurs ;
    private JournalAmelioarationCriteria journalAmelioaration ;
    private List<JournalAmelioarationCriteria> journalAmelioarations ;


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

    public LocalDateTime getDateEvenement(){
        return this.dateEvenement;
    }
    public void setDateEvenement(LocalDateTime dateEvenement){
        this.dateEvenement = dateEvenement;
    }
    public LocalDateTime getDateEvenementFrom(){
        return this.dateEvenementFrom;
    }
    public void setDateEvenementFrom(LocalDateTime dateEvenementFrom){
        this.dateEvenementFrom = dateEvenementFrom;
    }
    public LocalDateTime getDateEvenementTo(){
        return this.dateEvenementTo;
    }
    public void setDateEvenementTo(LocalDateTime dateEvenementTo){
        this.dateEvenementTo = dateEvenementTo;
    }
    public LocalDateTime getDateSoumission(){
        return this.dateSoumission;
    }
    public void setDateSoumission(LocalDateTime dateSoumission){
        this.dateSoumission = dateSoumission;
    }
    public LocalDateTime getDateSoumissionFrom(){
        return this.dateSoumissionFrom;
    }
    public void setDateSoumissionFrom(LocalDateTime dateSoumissionFrom){
        this.dateSoumissionFrom = dateSoumissionFrom;
    }
    public LocalDateTime getDateSoumissionTo(){
        return this.dateSoumissionTo;
    }
    public void setDateSoumissionTo(LocalDateTime dateSoumissionTo){
        this.dateSoumissionTo = dateSoumissionTo;
    }
    public String getAttachments(){
        return this.attachments;
    }
    public void setAttachments(String attachments){
        this.attachments = attachments;
    }
    public String getAttachmentsLike(){
        return this.attachmentsLike;
    }
    public void setAttachmentsLike(String attachmentsLike){
        this.attachmentsLike = attachmentsLike;
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

    public String getRecommendation(){
        return this.recommendation;
    }
    public void setRecommendation(String recommendation){
        this.recommendation = recommendation;
    }
    public String getRecommendationLike(){
        return this.recommendationLike;
    }
    public void setRecommendationLike(String recommendationLike){
        this.recommendationLike = recommendationLike;
    }

    public String getDirectivePmjChef(){
        return this.directivePmjChef;
    }
    public void setDirectivePmjChef(String directivePmjChef){
        this.directivePmjChef = directivePmjChef;
    }
    public String getDirectivePmjChefLike(){
        return this.directivePmjChefLike;
    }
    public void setDirectivePmjChefLike(String directivePmjChefLike){
        this.directivePmjChefLike = directivePmjChefLike;
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
      
    public String getWindDirection(){
        return this.windDirection;
    }
    public void setWindDirection(String windDirection){
        this.windDirection = windDirection;
    }   
    public String getWindDirectionMin(){
        return this.windDirectionMin;
    }
    public void setWindDirectionMin(String windDirectionMin){
        this.windDirectionMin = windDirectionMin;
    }
    public String getWindDirectionMax(){
        return this.windDirectionMax;
    }
    public void setWindDirectionMax(String windDirectionMax){
        this.windDirectionMax = windDirectionMax;
    }
      
    public String getWindForce(){
        return this.windForce;
    }
    public void setWindForce(String windForce){
        this.windForce = windForce;
    }   
    public String getWindForceMin(){
        return this.windForceMin;
    }
    public void setWindForceMin(String windForceMin){
        this.windForceMin = windForceMin;
    }
    public String getWindForceMax(){
        return this.windForceMax;
    }
    public void setWindForceMax(String windForceMax){
        this.windForceMax = windForceMax;
    }
      
    public String getCurrentDirection(){
        return this.currentDirection;
    }
    public void setCurrentDirection(String currentDirection){
        this.currentDirection = currentDirection;
    }   
    public String getCurrentDirectionMin(){
        return this.currentDirectionMin;
    }
    public void setCurrentDirectionMin(String currentDirectionMin){
        this.currentDirectionMin = currentDirectionMin;
    }
    public String getCurrentDirectionMax(){
        return this.currentDirectionMax;
    }
    public void setCurrentDirectionMax(String currentDirectionMax){
        this.currentDirectionMax = currentDirectionMax;
    }
      
    public String getCurrentForce(){
        return this.currentForce;
    }
    public void setCurrentForce(String currentForce){
        this.currentForce = currentForce;
    }   
    public String getCurrentForceMin(){
        return this.currentForceMin;
    }
    public void setCurrentForceMin(String currentForceMin){
        this.currentForceMin = currentForceMin;
    }
    public String getCurrentForceMax(){
        return this.currentForceMax;
    }
    public void setCurrentForceMax(String currentForceMax){
        this.currentForceMax = currentForceMax;
    }
      
    public String getSwellHeigth(){
        return this.swellHeigth;
    }
    public void setSwellHeigth(String swellHeigth){
        this.swellHeigth = swellHeigth;
    }   
    public String getSwellHeigthMin(){
        return this.swellHeigthMin;
    }
    public void setSwellHeigthMin(String swellHeigthMin){
        this.swellHeigthMin = swellHeigthMin;
    }
    public String getSwellHeigthMax(){
        return this.swellHeigthMax;
    }
    public void setSwellHeigthMax(String swellHeigthMax){
        this.swellHeigthMax = swellHeigthMax;
    }
      
    public String getSwellDirection(){
        return this.swellDirection;
    }
    public void setSwellDirection(String swellDirection){
        this.swellDirection = swellDirection;
    }   
    public String getSwellDirectionMin(){
        return this.swellDirectionMin;
    }
    public void setSwellDirectionMin(String swellDirectionMin){
        this.swellDirectionMin = swellDirectionMin;
    }
    public String getSwellDirectionMax(){
        return this.swellDirectionMax;
    }
    public void setSwellDirectionMax(String swellDirectionMax){
        this.swellDirectionMax = swellDirectionMax;
    }
      
    public Boolean getFonde(){
        return this.fonde;
    }
    public void setFonde(Boolean fonde){
        this.fonde = fonde;
    }

    public PortCriteria getPort(){
        return this.port;
    }

    public void setPort(PortCriteria port){
        this.port = port;
    }
    public List<PortCriteria> getPorts(){
        return this.ports;
    }

    public void setPorts(List<PortCriteria> ports){
        this.ports = ports;
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
    public VesselCriteria getVessel(){
        return this.vessel;
    }

    public void setVessel(VesselCriteria vessel){
        this.vessel = vessel;
    }
    public List<VesselCriteria> getVessels(){
        return this.vessels;
    }

    public void setVessels(List<VesselCriteria> vessels){
        this.vessels = vessels;
    }
    public TypeRapportEvenementCriteria getTypeRapportEvenement(){
        return this.typeRapportEvenement;
    }

    public void setTypeRapportEvenement(TypeRapportEvenementCriteria typeRapportEvenement){
        this.typeRapportEvenement = typeRapportEvenement;
    }
    public List<TypeRapportEvenementCriteria> getTypeRapportEvenements(){
        return this.typeRapportEvenements;
    }

    public void setTypeRapportEvenements(List<TypeRapportEvenementCriteria> typeRapportEvenements){
        this.typeRapportEvenements = typeRapportEvenements;
    }
    public SecteurCriteria getSecteur(){
        return this.secteur;
    }

    public void setSecteur(SecteurCriteria secteur){
        this.secteur = secteur;
    }
    public List<SecteurCriteria> getSecteurs(){
        return this.secteurs;
    }

    public void setSecteurs(List<SecteurCriteria> secteurs){
        this.secteurs = secteurs;
    }
    public JournalAmelioarationCriteria getJournalAmelioaration(){
        return this.journalAmelioaration;
    }

    public void setJournalAmelioaration(JournalAmelioarationCriteria journalAmelioaration){
        this.journalAmelioaration = journalAmelioaration;
    }
    public List<JournalAmelioarationCriteria> getJournalAmelioarations(){
        return this.journalAmelioarations;
    }

    public void setJournalAmelioarations(List<JournalAmelioarationCriteria> journalAmelioarations){
        this.journalAmelioarations = journalAmelioarations;
    }
}
