package  ma.zyn.app.ws.dto.rapport;

import ma.zyn.app.zynerator.dto.AuditBaseDto;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;


import ma.zyn.app.ws.dto.actor.CollaboratorDto;
import ma.zyn.app.ws.dto.commun.TypeRapportEvenementDto;
import ma.zyn.app.ws.dto.commun.VesselDto;
import ma.zyn.app.ws.dto.commun.PortDto;
import ma.zyn.app.ws.dto.commun.SecteurDto;
import ma.zyn.app.ws.dto.commun.TypeEvenementDto;
import ma.zyn.app.ws.dto.journal.JournalAmelioarationDto;


@JsonInclude(JsonInclude.Include.NON_NULL)
public class RapportEvenementDto  extends AuditBaseDto {

    private String code  ;
    private String dateEvenement ;
    private String dateSoumission ;
    private String attachments  ;
    private String description  ;
    private String recommendation  ;
    private String directivePmjChef  ;
    private Integer mois  = 0 ;
    private Integer annee  = 0 ;
    private BigDecimal windDirection  ;
    private BigDecimal windForce  ;
    private BigDecimal currentDirection  ;
    private BigDecimal currentForce  ;
    private BigDecimal swellHeigth  ;
    private BigDecimal swellDirection  ;
    private Boolean fonde  ;

    private PortDto port ;
    private TypeEvenementDto typeEvenement ;
    private CollaboratorDto collaborator ;
    private VesselDto vessel ;
    private TypeRapportEvenementDto typeRapportEvenement ;
    private SecteurDto secteur ;
    private JournalAmelioarationDto journalAmelioaration ;



    public RapportEvenementDto(){
        super();
    }




    public String getCode(){
        return this.code;
    }
    public void setCode(String code){
        this.code = code;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateEvenement(){
        return this.dateEvenement;
    }
    public void setDateEvenement(String dateEvenement){
        this.dateEvenement = dateEvenement;
    }


    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy HH:mm")
    public String getDateSoumission(){
        return this.dateSoumission;
    }
    public void setDateSoumission(String dateSoumission){
        this.dateSoumission = dateSoumission;
    }


    public String getAttachments(){
        return this.attachments;
    }
    public void setAttachments(String attachments){
        this.attachments = attachments;
    }


    public String getDescription(){
        return this.description;
    }
    public void setDescription(String description){
        this.description = description;
    }


    public String getRecommendation(){
        return this.recommendation;
    }
    public void setRecommendation(String recommendation){
        this.recommendation = recommendation;
    }


    public String getDirectivePmjChef(){
        return this.directivePmjChef;
    }
    public void setDirectivePmjChef(String directivePmjChef){
        this.directivePmjChef = directivePmjChef;
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


    public BigDecimal getWindDirection(){
        return this.windDirection;
    }
    public void setWindDirection(BigDecimal windDirection){
        this.windDirection = windDirection;
    }


    public BigDecimal getWindForce(){
        return this.windForce;
    }
    public void setWindForce(BigDecimal windForce){
        this.windForce = windForce;
    }


    public BigDecimal getCurrentDirection(){
        return this.currentDirection;
    }
    public void setCurrentDirection(BigDecimal currentDirection){
        this.currentDirection = currentDirection;
    }


    public BigDecimal getCurrentForce(){
        return this.currentForce;
    }
    public void setCurrentForce(BigDecimal currentForce){
        this.currentForce = currentForce;
    }


    public BigDecimal getSwellHeigth(){
        return this.swellHeigth;
    }
    public void setSwellHeigth(BigDecimal swellHeigth){
        this.swellHeigth = swellHeigth;
    }


    public BigDecimal getSwellDirection(){
        return this.swellDirection;
    }
    public void setSwellDirection(BigDecimal swellDirection){
        this.swellDirection = swellDirection;
    }


    public Boolean getFonde(){
        return this.fonde;
    }
    public void setFonde(Boolean fonde){
        this.fonde = fonde;
    }


    public PortDto getPort(){
        return this.port;
    }

    public void setPort(PortDto port){
        this.port = port;
    }
    public TypeEvenementDto getTypeEvenement(){
        return this.typeEvenement;
    }

    public void setTypeEvenement(TypeEvenementDto typeEvenement){
        this.typeEvenement = typeEvenement;
    }
    public CollaboratorDto getCollaborator(){
        return this.collaborator;
    }

    public void setCollaborator(CollaboratorDto collaborator){
        this.collaborator = collaborator;
    }
    public VesselDto getVessel(){
        return this.vessel;
    }

    public void setVessel(VesselDto vessel){
        this.vessel = vessel;
    }
    public TypeRapportEvenementDto getTypeRapportEvenement(){
        return this.typeRapportEvenement;
    }

    public void setTypeRapportEvenement(TypeRapportEvenementDto typeRapportEvenement){
        this.typeRapportEvenement = typeRapportEvenement;
    }
    public SecteurDto getSecteur(){
        return this.secteur;
    }

    public void setSecteur(SecteurDto secteur){
        this.secteur = secteur;
    }
    public JournalAmelioarationDto getJournalAmelioaration(){
        return this.journalAmelioaration;
    }

    public void setJournalAmelioaration(JournalAmelioarationDto journalAmelioaration){
        this.journalAmelioaration = journalAmelioaration;
    }






}
